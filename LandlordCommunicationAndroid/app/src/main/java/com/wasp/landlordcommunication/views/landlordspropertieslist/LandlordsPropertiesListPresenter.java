package com.wasp.landlordcommunication.views.landlordspropertieslist;

import javax.inject.Inject;

public class LandlordsPropertiesListPresenter implements LandlordsPropertiesListContracts.Presenter {

    private LandlordsPropertiesListContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public LandlordsPropertiesListPresenter() {

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
}
