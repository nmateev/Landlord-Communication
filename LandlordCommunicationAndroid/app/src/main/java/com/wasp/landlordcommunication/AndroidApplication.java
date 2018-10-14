package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.diconfig.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AndroidApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerAppComponent
                .builder()
                .application(this)
                .build();

    }
}
