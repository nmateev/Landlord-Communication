package com.wasp.landlordcommunication.diconfig;


import com.wasp.landlordcommunication.diconfig.viewsmodules.CameraModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LoginModule;
import com.wasp.landlordcommunication.views.camera.CameraActivity;
import com.wasp.landlordcommunication.views.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();
    @ActivityScoped
    @ContributesAndroidInjector(modules = CameraModule.class)
    abstract CameraActivity cameraActivity();
}
