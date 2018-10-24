package com.wasp.landlordcommunication.views.camera;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class CameraActivity extends DaggerAppCompatActivity {
    @Inject
    CameraFragment mCameraFragment;
    @Inject
    CameraContracts.Presenter mCameraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ButterKnife.bind(this);

       mCameraFragment.setPresenter(mCameraPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mCameraFragment)
                .commit();


    }
}
