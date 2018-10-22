package com.wasp.landlordcommunication.views.camera;

public class CameraPresenter implements CameraContracts.Presenter {
    private CameraContracts.View mView;


    @Override
    public void subscribe(CameraContracts.View view) {
        mView=view;
    }

    @Override
    public void unsubscribe() {
        mView=null;
    }
}
