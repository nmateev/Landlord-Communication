package com.wasp.landlordcommunication.diconfig;

import android.app.Application;

import com.wasp.landlordcommunication.AndroidApplication;
import com.wasp.landlordcommunication.diconfig.viewsmodules.ChatListModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.ChatModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.HomeModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.ImageViewModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LandlordPropertyDetailsModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LandlordsListModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LandlordsPropertiesListModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LoginModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.PaymentsModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.PropertiesModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.PropertyManagementModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.SettingsModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.SignUpModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.ViewsModule;

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
        ViewsModule.class,
        LoginModule.class,
        SignUpModule.class,
        HomeModule.class,
        LandlordsListModule.class,
        LandlordsPropertiesListModule.class,
        LandlordPropertyDetailsModule.class,
        PropertiesModule.class,
        PropertyManagementModule.class,
        ChatListModule.class,
        ChatModule.class,
        ImageViewModule.class,
        PaymentsModule.class,
        SettingsModule.class

})

public interface AppComponent extends AndroidInjector<AndroidApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
