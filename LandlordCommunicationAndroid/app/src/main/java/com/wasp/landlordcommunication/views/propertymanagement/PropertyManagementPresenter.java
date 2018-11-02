package com.wasp.landlordcommunication.views.propertymanagement;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.LANDLORD;
import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class PropertyManagementPresenter implements PropertyManagementContracts.Presenter {
    private final PropertiesService mPropertiesService;
    private final RatingsService mRatingsService;
    private final SchedulerProvider mSchedulerProvider;
    private final ImageEncoder mImageEncoder;
    private PropertyManagementContracts.View mView;
    private int mUserId;
    private String mUserType;
    int mOtherUserId;
    private int mSelectedPropertyId;

    @Inject
    public PropertyManagementPresenter(PropertiesService propertiesService, RatingsService ratingsService, SchedulerProvider schedulerProvider, ImageEncoder imageEncoder) {
        mPropertiesService = propertiesService;
        mRatingsService = ratingsService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
    }

    @Override
    public void subscribe(PropertyManagementContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void setUserType(String userType) {
        mUserType = userType;
    }

    @Override
    public void setSelectedPropertyId(int propertyId) {
        mSelectedPropertyId = propertyId;
    }

    @Override
    public void loadPropertyManagementOptions() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.getPropertyById(mSelectedPropertyId);
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(() -> mView.hideProgressBar())
                .subscribe(this::preparePropertyDetails,
                        error -> mView.showError(error));


    }

    @Override
    public void rateButtonIsClicked() {
        mView.showRatingDialog();
    }

    @Override
    public void ratingWasCancelled() {
        mView.showMessage(Constants.RATING_CANCELLED_MESSAGE);
    }

    @Override
    public void userIsRated(int ratingValue) {

        Rating ratingConnectionToCheck = new Rating(mUserId, mOtherUserId);
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating ratingCheck = mRatingsService.checkIfUserAlreadyRatedByVoter(ratingConnectionToCheck);
                    emitter.onNext(ratingCheck);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userRatingResult -> mView.showMessage(Constants.ALREADY_RATED_MESSAGE),
                        error -> {
                            if (error instanceof NullPointerException) {
                                submitUserRating(ratingValue, mUserId, mOtherUserId);
                            } else {
                                mView.showError(error);
                            }
                        });


    }

    private void submitUserRating(int ratingValue, int voterId, int votedForId) {
        Rating newRating = new Rating(voterId, votedForId, ratingValue);

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating rating = mRatingsService.submitRating(newRating);
                    emitter.onNext(rating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(rating -> mView.showMessage(Constants.SUCCESSFUL_RATING),
                        error -> {
                            if (error instanceof NullPointerException) {
                                mView.showMessage(Constants.UNEXPECTED_ERROR);
                            } else {
                                mView.showError(error);
                            }
                        });

    }

    private void preparePropertyDetails(Property property) {
        if (Objects.equals(property.getPropertyPicture(), null)) {
            mView.showDefaultPropertyPicture();
        } else {
            Bitmap propertyImage = mImageEncoder.decodeStringToBitmap(property.getPropertyPicture());
            if (Objects.equals(propertyImage, null)) {
                mView.showDefaultPropertyPicture();
            } else {
                mView.showPropertyPicture(propertyImage);
            }
        }
        String individualisation;
        if (mUserType.equals(TENANT)) {
            individualisation = LANDLORD;
        } else {
            individualisation = TENANT;
        }

        if (mUserId == property.getTenantId()) {
            mOtherUserId = property.getLandlordId();
        } else {
            mOtherUserId = property.getTenantId();
        }

        mView.showPropertyDetails(property, individualisation);

        if (mUserType.equals(TENANT)) {
            mView.showPayButtonOption();
        }
    }
}
