package com.wasp.landlordcommunication.views.properties;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class PropertiesPresenter implements PropertiesContracts.Presenter {


    private final PropertiesService mPropertiesService;
    private final SchedulerProvider mSchedulerProvider;
    private PropertiesContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public PropertiesPresenter(PropertiesService propertiesService, SchedulerProvider schedulerProvider) {
        mPropertiesService = propertiesService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(PropertiesContracts.View view) {
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
    public void loadUserProperties(String preference) {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Property>>) emitter -> {
                    List<Property> properties = mPropertiesService.getUsersPropertiesByIdAndType(mUserId, mUserType);
                    emitter.onNext(properties);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(propertiesResult -> presentPropertiesAccordingToPreference(propertiesResult, preference),
                        error -> mView.showError(error));


    }

    @Override
    public void propertyIsSelected(Property selectedProperty) {

    }

    private void presentPropertiesAccordingToPreference(List<Property> propertiesResult, String preference) {

        if (propertiesResult.isEmpty()) {
            if (mUserType.equals(TENANT)) {
                mView.showTextMessageOnScreen(Constants.NO_RENTED_PLACES_MESSAGE);
            } else {
                mView.showTextMessageOnScreen(Constants.NO_PROPERTIES_FOR_RENT_MESSAGE);
            }
        } else {
            //if there is no preference chosen already or the preference is compact view
            if (preference.equals(Constants.EMPTY_STRING) || preference.equals(Constants.COMPACT_VIEW_STYLE)) {
                mView.showCompactPropertiesView(propertiesResult);
            } else {
                mView.showDetailedPropertiesView(propertiesResult);
            }
        }

    }
}
