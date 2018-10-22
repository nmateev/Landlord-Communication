package com.wasp.landlordcommunication.views.camera;

import android.hardware.Camera;
import android.os.Bundle;
import android.widget.ImageView;

public interface CameraContracts {

    interface View{
        void captureImage(Camera deviceCamera);
        void sendImage(ImageView image);
        void setPresenter(Presenter presenter);
    }

    interface Presenter{
        void subscribe(View view);
        void unsubscribe();
    }
}
