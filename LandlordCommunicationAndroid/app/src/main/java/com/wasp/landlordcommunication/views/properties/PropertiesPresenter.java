package com.wasp.landlordcommunication.views.properties;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.services.base.PropertiesService;

import javax.inject.Inject;

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
}
