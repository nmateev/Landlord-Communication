package com.wasp.landlordcommunication.views.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wasp.landlordcommunication.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity {
    @Inject
    CameraFragment mCameraFragment;
    @Inject
    CameraPresenter mCameraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ButterKnife.bind(this);

       mCameraFragment.setPresenter(mCameraPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mCameraFragment)
                .commit();


    }
}
