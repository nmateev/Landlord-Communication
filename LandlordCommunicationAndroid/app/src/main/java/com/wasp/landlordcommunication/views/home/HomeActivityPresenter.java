package com.wasp.landlordcommunication.views.home;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.BitmapCacheRepository;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class HomeActivityPresenter implements HomeActivityContracts.Presenter {

    private final UsersService mUsersService;
    private final RatingsService mRatingsService;
    private final SchedulerProvider mSchedulerProvider;
    private final PropertiesService mPropertiesService;
    private final ImageEncoder mImageEncoder;
    private final BitmapCacheRepository mBitmapCacheRepository;
    private HomeActivityContracts.View mView;
    private String mUserName;
    private int mUserId;
    private String mUserType;

    @Inject
    public HomeActivityPresenter(UsersService usersService, RatingsService ratingsService, PropertiesService propertiesService, SchedulerProvider schedulerProvider, ImageEncoder imageEncoder, BitmapCacheRepository bitmapCacheRepository) {
        mUsersService = usersService;
        mRatingsService = ratingsService;
        mPropertiesService = propertiesService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
        mBitmapCacheRepository = bitmapCacheRepository;
    }

    @Override
    public void subscribe(HomeActivityContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }


    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void selectPictureFromGalleryButtonClickIsClicked() {
        mView.showOptionToChooseImage();
    }

    @Override
    public void takePictureButtonIsClicked() {
        mView.presentOptionToTakePicture();
    }

    @Override
    public void newImageIsChosen(Bitmap image) {
        String imageString = mImageEncoder.encodeBitmapToString(image);

        if (Objects.equals(imageString, null)) {
            mView.showMessage(Constants.IMAGE_CHANGE_ERROR_MESSAGE);
        } else {
            mView.showProgressBar();
            Disposable observable = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User user = mUsersService.getUserByUserName(mUserName);
                        emitter.onNext(user);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.backgroundThread())
                    .observeOn(mSchedulerProvider.uiThread())
                    .doFinally(mView::hideProgressBar)
                    .subscribe(userResult -> updateUserPicture(userResult, imageString),
                            error -> mView.showError(error));
        }

    }

    @Override
    public void updateUserPicture(User user, String imageString) {
        if (!Objects.equals(user, null)) {
            user.setUserPicture(imageString);

            mView.showProgressBar();
            Disposable observable = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User userToUpdate = mUsersService.updateUser(user, user.getUserId());
                        emitter.onNext(userToUpdate);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.backgroundThread())
                    .observeOn(mSchedulerProvider.uiThread())
                    .doFinally(mView::hideProgressBar)
                    .subscribe(userResult -> {
                                if (Objects.equals(userResult.getUserPicture(), null)) {
                                    mView.showMessage(Constants.IMAGE_CHANGE_ERROR_MESSAGE);
                                } else {
                                    decodeImageAndPresentToView(userResult.getUserPicture(), Constants.IMAGE_CHANGE_ERROR_MESSAGE);
                                }
                            },
                            error -> mView.showError(error));
        } else {
            mView.showMessage(Constants.IMAGE_CHANGE_ERROR_MESSAGE);
        }
    }

    @Override
    public void decodeImageAndPresentToView(String userPicture, String errorMessage) {

        Bitmap decodedUserPicture = mImageEncoder.decodeStringToBitmap(userPicture);
        if (Objects.equals(decodedUserPicture, null)) {
            mView.showMessage(errorMessage);
        } else {
            mBitmapCacheRepository.addBitmapToBitmapCache(decodedUserPicture, Constants.USER_PROFILE_IMAGE_KEY);
            mView.showUserImage(decodedUserPicture);
        }
    }

    @Override
    public void setUserType(String userType) {

        mUserType = userType;
    }

    @Override
    public void errorOccurredOnChangingPicture() {
        mView.showMessage(Constants.IMAGE_CHANGE_ERROR_MESSAGE);
    }

    @Override
    public void loadUserInformation() {
        loadUserPictureAndName();
        loadUserRating();
    }

    @Override
    public void loadUserPictureAndName() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mUsersService.getUserByUserName(mUserName);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userResult -> {
                            if (!Objects.equals(userResult.getUserPicture(), null)) {
                                decodeImageAndPresentToView(userResult.getUserPicture(), Constants.ERROR_LOADING_USER_IMAGE);
                            }
                            String fullName = userResult.getFirstName() + " " + userResult.getLastName();
                            mView.showUserName(fullName);
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void loadUserRating() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Double>) emitter -> {
                    double userRating = mRatingsService.getUserRatingById(mUserId);
                    emitter.onNext(userRating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userRatingResult -> mView.showUserRating(userRatingResult),
                        error -> mView.showError(error));
    }

    @Override
    public void updatePropertiesPaidStatus() {
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Property>>) emitter -> {
                    List<Property> properties = mPropertiesService.getUsersPropertiesByIdAndType(mUserId, mUserType);
                    emitter.onNext(properties);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(this::checkIfPaidStatusShouldBeUpdated,
                        error -> mView.showError(error));

    }

    private void checkIfPaidStatusShouldBeUpdated(List<Property> propertiesResult) {
        Calendar now = Calendar.getInstance();
        int currentDay = now.get(Calendar.DAY_OF_MONTH);

        for (Property property : propertiesResult) {
            if (property.getRentPaid()) {
                if (currentDay > property.getDueDate()) {
                    setPaidStatusFalseForProperty(property);
                }
            }
        }
    }

    private void setPaidStatusFalseForProperty(Property propertyToUpdate) {
        propertyToUpdate.setRentPaid(false);

        //setting property picture to null in order to transfer little data to speed up process, picture is not deleted in the database
        propertyToUpdate.setPropertyPicture(null);

        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.updateProperty(propertyToUpdate, propertyToUpdate.getPropertyId());
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(property -> {
                }, error -> mView.showError(error));
    }
}
