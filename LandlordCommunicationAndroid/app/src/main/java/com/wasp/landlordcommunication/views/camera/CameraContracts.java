package com.wasp.landlordcommunication.views.camera;

import android.widget.ImageView;

public interface CameraContracts {

    interface View {
        void sendImage(ImageView image);

        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void subscribe(View view);

        void unsubscribe();
    }
}
