package com.wasp.landlordcommunication.views.landlordpropertydetails;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LandlordPropertyDetailsPresenter implements LandlordPropertyDetailsContracts.Presenter {
    private final PropertiesService mPropertiesService;
    private final SchedulerProvider mSchedulerProvider;
    private final ImageEncoder mImageEncoder;
    private LandlordPropertyDetailsContracts.View mView;
    private int mUserId;
    private String mUserType;
    private int mSelectedPropertyId;

    @Inject
    public LandlordPropertyDetailsPresenter(PropertiesService propertiesService, SchedulerProvider schedulerProvider, ImageEncoder imageEncoder) {
        mPropertiesService = propertiesService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
    }

    @Override
    public void subscribe(LandlordPropertyDetailsContracts.View view) {
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
    public void loadPropertyDetails() {

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.getPropertyById(mSelectedPropertyId);
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(this::preparePropertyDetails,
                        error -> mView.showError(error));
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
        mView.showPropertyDetails(property);

    }
}
