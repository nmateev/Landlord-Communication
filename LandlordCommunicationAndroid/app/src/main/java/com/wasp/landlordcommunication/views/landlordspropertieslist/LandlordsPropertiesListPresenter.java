package com.wasp.landlordcommunication.views.landlordspropertieslist;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LandlordsPropertiesListPresenter implements LandlordsPropertiesListContracts.Presenter {
    private final RatingsService mRatingsService;
    private final PropertiesService mPropertiesService;
    private final SchedulerProvider mSchedulerProvider;
    private LandlordsPropertiesListContracts.View mView;
    private int mUserId;
    private String mUserType;
    private User mSelectedLandlord;

    @Inject
    public LandlordsPropertiesListPresenter(RatingsService ratingsService, PropertiesService propertiesService, SchedulerProvider schedulerProvider) {
        mRatingsService = ratingsService;
        mPropertiesService = propertiesService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(LandlordsPropertiesListContracts.View view) {
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
    public void setSelectedLandlord(User selectedLandlord) {
        mSelectedLandlord = selectedLandlord;
    }

    @Override
    public void loadLandlordsData() {
        mView.showLandlordsName(mSelectedLandlord.getFirstName() + " " + mSelectedLandlord.getLastName());
        loadLandlordsRating(mSelectedLandlord.getUserId());
        loadLandlordsProperties(mSelectedLandlord.getUserId(), mSelectedLandlord.getUserType());
    }

    @Override
    public void loadLandlordsRating(int userId) {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Double>) emitter -> {
                    double landlordsRating = mRatingsService.getUserRatingById(mSelectedLandlord.getUserId());
                    emitter.onNext(landlordsRating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(landlordsRating -> mView.showLandlordsRating(landlordsRating),
                        error -> mView.showError(error));
    }

    @Override
    public void loadLandlordsProperties(int userId, String userType) {
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Property>>) emitter -> {
                    List<Property> landlordsPropertiesList = mPropertiesService.getUsersPropertiesByIdAndType(mSelectedLandlord.getUserId(), mSelectedLandlord.getUserType());
                    emitter.onNext(landlordsPropertiesList);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(this::presentPropertiesToView,
                        error -> mView.showError(error));
    }

    private void presentPropertiesToView(List<Property> properties) {

        if (properties.isEmpty()) {
            mView.showNoPropertiesText(Constants.NO_PROPERTIES_MESSAGE);
        } else {
            mView.showLandlordsProperties(properties);
        }
    }
}
