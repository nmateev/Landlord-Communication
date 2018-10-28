package com.wasp.landlordcommunication.views.landlordslist;

import javax.inject.Inject;

public class LandlordsListPresenter implements LandlordsListContracts.Presenter {

    private LandlordsListContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public LandlordsListPresenter() {


    }


    @Override
    public void subscribe(LandlordsListContracts.View view) {
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
    public void showAllLandlords() {

    }
}
