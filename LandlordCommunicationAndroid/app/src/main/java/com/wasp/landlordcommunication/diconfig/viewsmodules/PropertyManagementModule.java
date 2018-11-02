package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.utils.DateFormatter;
import com.wasp.landlordcommunication.views.propertymanagement.PropertyManagementContracts;
import com.wasp.landlordcommunication.views.propertymanagement.PropertyManagementFragment;
import com.wasp.landlordcommunication.views.propertymanagement.PropertyManagementPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PropertyManagementModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PropertyManagementFragment propertyManagementFragment();

    @ActivityScoped
    @Binds
    abstract PropertyManagementContracts.Presenter propertyManagementPresenter(PropertyManagementPresenter propertyManagementPresenter);


}
