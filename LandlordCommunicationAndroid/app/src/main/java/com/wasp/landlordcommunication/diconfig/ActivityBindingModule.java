package com.wasp.landlordcommunication.diconfig;


import com.wasp.landlordcommunication.diconfig.viewsmodules.CameraModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.ChatModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.HomeModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LandlordPropertyDetailsModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LandlordsListModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LandlordsPropertiesListModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.LoginModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.PaymentsModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.PropertiesModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.PropertyManagementModule;
import com.wasp.landlordcommunication.diconfig.viewsmodules.SignUpModule;
import com.wasp.landlordcommunication.views.camera.CameraActivity;
import com.wasp.landlordcommunication.views.chat.ChatActivity;
import com.wasp.landlordcommunication.views.home.HomeActivity;
import com.wasp.landlordcommunication.views.home.HomeFragment;
import com.wasp.landlordcommunication.views.landlordpropertydetails.LandlordPropertyDetailsActivity;
import com.wasp.landlordcommunication.views.landlordslist.LandlordsListActivity;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesListActivity;
import com.wasp.landlordcommunication.views.login.LoginActivity;
import com.wasp.landlordcommunication.views.payments.PaymentsActivity;
import com.wasp.landlordcommunication.views.properties.PropertiesActivity;
import com.wasp.landlordcommunication.views.propertymanagement.PropertyManagementActivity;
import com.wasp.landlordcommunication.views.signup.SignUpActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SignUpModule.class)
    abstract SignUpActivity signUpActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PaymentsModule.class)
    abstract PaymentsActivity paymentsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LandlordsListModule.class)
    abstract LandlordsListActivity landlordsListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LandlordsPropertiesListModule.class)
    abstract LandlordsPropertiesListActivity landlordsPropertiesListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LandlordPropertyDetailsModule.class)
    abstract LandlordPropertyDetailsActivity landlordPropertyDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PropertiesModule.class)
    abstract PropertiesActivity propertiesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PropertyManagementModule.class)
    abstract PropertyManagementActivity propertyManagementActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ChatModule.class)
    abstract ChatActivity chatActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = CameraModule.class)
    abstract CameraActivity cameraActivity();
}
