package com.wasp.landlordcommunication.views.home;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class HomeActivityPresenter implements HomeActivityContracts.Presenter {

    private final UsersService mUsersService;
    private final RatingsService mRatingsService;
    private final SchedulerProvider mSchedulerProvider;
    private final ImageEncoder mImageEncoder;
    private HomeActivityContracts.View mView;
    private String mUserName;
    private int mUserId;

    @Inject
    public HomeActivityPresenter(UsersService usersService, RatingsService ratingsService, SchedulerProvider schedulerProvider, ImageEncoder imageEncoder) {
        mUsersService = usersService;
        mRatingsService = ratingsService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
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
                                    decodeImageAndPresentToView(userResult.getUserPicture(),Constants.IMAGE_CHANGE_ERROR_MESSAGE);
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
            mView.showUserImage(decodedUserPicture);
        }
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
                                decodeImageAndPresentToView(userResult.getUserPicture(),Constants.ERROR_LOADING_USER_IMAGE);
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
}
