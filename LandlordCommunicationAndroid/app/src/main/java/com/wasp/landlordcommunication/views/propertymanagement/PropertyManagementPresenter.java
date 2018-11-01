package com.wasp.landlordcommunication.views.propertymanagement;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.services.base.PropertiesService;

import javax.inject.Inject;

public class PropertyManagementPresenter implements PropertyManagementContracts.Presenter {
    private final PropertiesService mPropertiesService;
    private final SchedulerProvider mSchedulerProvider;
    private PropertyManagementContracts.View mView;
    private int mUserId;
    private String mUserType;
    private int mSelectedPropertyId;

    @Inject
    public PropertyManagementPresenter(PropertiesService propertiesService, SchedulerProvider schedulerProvider) {
        mPropertiesService = propertiesService;
        mSchedulerProvider = schedulerProvider;
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

        

    }
}
