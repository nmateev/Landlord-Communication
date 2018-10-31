package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.properties.PropertiesContracts;
import com.wasp.landlordcommunication.views.properties.PropertiesFragment;
import com.wasp.landlordcommunication.views.properties.PropertiesPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PropertiesModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract PropertiesFragment propertiesFragment();

    @ActivityScoped
    @Binds
    abstract PropertiesContracts.Presenter propertiesPresenter(PropertiesPresenter propertiesPresenter);
}
