package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.camera.CameraContracts;
import com.wasp.landlordcommunication.views.camera.CameraFragment;
import com.wasp.landlordcommunication.views.camera.CameraPresenter;
import com.wasp.landlordcommunication.views.login.LoginContracts;
import com.wasp.landlordcommunication.views.login.LoginFragment;
import com.wasp.landlordcommunication.views.login.LoginPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CameraModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract CameraFragment cameraFragment();

    @ActivityScoped
    @Binds
    abstract CameraContracts.Presenter cameraPresenter(CameraPresenter cameraPresenter);

}
