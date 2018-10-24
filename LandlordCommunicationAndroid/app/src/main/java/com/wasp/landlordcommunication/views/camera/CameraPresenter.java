package com.wasp.landlordcommunication.views.camera;

import javax.inject.Inject;

public class CameraPresenter implements CameraContracts.Presenter {
    private CameraContracts.View mView;

    @Inject
    CameraPresenter(){

    }

    @Override
    public void subscribe(CameraContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
