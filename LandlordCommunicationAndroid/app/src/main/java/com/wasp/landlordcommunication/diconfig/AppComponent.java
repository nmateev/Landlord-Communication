package com.wasp.landlordcommunication.diconfig;

import android.app.Application;
import com.wasp.landlordcommunication.AndroidApplication;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LoginModule;

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
        LoginModule.class
})

public interface AppComponent extends AndroidInjector<AndroidApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}