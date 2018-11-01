package com.wasp.landlordcommunication.views.properties;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.FILTER_OPTION_ALL;
import static com.wasp.landlordcommunication.utils.Constants.FILTER_OPTION_ASCENDING_PRICE;
import static com.wasp.landlordcommunication.utils.Constants.FILTER_OPTION_DESCENDING_PRICE;
import static com.wasp.landlordcommunication.utils.Constants.FILTER_OPTION_NOT_PAID;
import static com.wasp.landlordcommunication.utils.Constants.FILTER_OPTION_PAID;
import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class PropertiesPresenter implements PropertiesContracts.Presenter {


    private final PropertiesService mPropertiesService;
    private final SchedulerProvider mSchedulerProvider;
    private PropertiesContracts.View mView;
    private int mUserId;
    private String mUserType;
    private String mCurrentSelectedOption;

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
        mCurrentSelectedOption = FILTER_OPTION_ALL;
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
                .subscribe(propertiesResult -> presentPropertiesToView(propertiesResult, preference),
                        error -> mView.showError(error));


    }

    @Override
    public void propertyIsSelected(Property selectedProperty) {
        mView.showPropertyManagementOptions(selectedProperty.getPropertyId());
    }

    @Override
    public void filterPropertiesWithOption(String preference, String selectedOption) {
        if (selectedOption.equals(mCurrentSelectedOption)) {
            return;
        }
        filterProperties(preference, selectedOption);
        mCurrentSelectedOption = selectedOption;
    }

    private void presentPropertiesToView(List<Property> propertiesResult, String preference) {
        if (propertiesResult.isEmpty()) {
            if (mUserType.equals(TENANT)) {
                mView.showTextMessageOnScreen(Constants.NO_RENTED_PLACES_MESSAGE);
            } else {
                mView.showTextMessageOnScreen(Constants.NO_PROPERTIES_FOR_RENT_MESSAGE);
            }
        } else {
            presentPropertiesToViewAccordingToPreference(propertiesResult, preference);
        }
    }

    private void presentPropertiesToViewAccordingToPreference(List<Property> propertiesResult, String preference) {

        //if there is no preference chosen already or the preference is compact view
        if (preference.equals(Constants.EMPTY_STRING) || preference.equals(Constants.COMPACT_VIEW_STYLE)) {
            mView.showCompactPropertiesView(propertiesResult);
        } else {
            mView.showDetailedPropertiesView(propertiesResult);
        }
    }

    private void filterProperties(String preference, String selectedOption) {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Property>>) emitter -> {
                    List<Property> properties = new ArrayList<>();
                    switch (selectedOption) {
                        case FILTER_OPTION_ALL:
                            properties = mPropertiesService.getUsersPropertiesByIdAndType(mUserId, mUserType);
                            break;
                        case FILTER_OPTION_PAID:
                            properties = mPropertiesService.getPropertiesByIdAndTypeOnlyPaidStatus(mUserId, mUserType);
                            break;
                        case FILTER_OPTION_NOT_PAID:
                            properties = mPropertiesService.getPropertiesByIdAndTypeOnlyNotPaidStatus(mUserId, mUserType);
                            break;
                        case FILTER_OPTION_ASCENDING_PRICE:
                            properties = mPropertiesService.getPropertiesByIdAndTypeSortAscending(mUserId, mUserType);
                            break;
                        case FILTER_OPTION_DESCENDING_PRICE:
                            properties = mPropertiesService.getPropertiesByIdAndTypeSortDescending(mUserId, mUserType);
                            break;
                    }
                    emitter.onNext(properties);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(propertiesResult -> presentPropertiesToViewAccordingToPreference(propertiesResult, preference),
                        error -> mView.showError(error));

    }
}
