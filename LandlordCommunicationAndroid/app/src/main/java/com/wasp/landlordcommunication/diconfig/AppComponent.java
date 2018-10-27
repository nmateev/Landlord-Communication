package com.wasp.landlordcommunication.diconfig;

import android.app.Application;

import com.wasp.landlordcommunication.AndroidApplication;
import com.wasp.landlordcommunication.diconfig.viewsmodules.CameraModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.HomeModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LoginModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.SignUpModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        HttpModule.class,
        AsyncModule.class,
        ParsersModule.class,
        ServicesModule.class,
        RepositoriesModule.class,
        UtilitiesModule.class,
        LoginModule.class,
        SignUpModule.class,
        CameraModule.class,
        HomeModule.class
})

public interface AppComponent extends AndroidInjector<AndroidApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
