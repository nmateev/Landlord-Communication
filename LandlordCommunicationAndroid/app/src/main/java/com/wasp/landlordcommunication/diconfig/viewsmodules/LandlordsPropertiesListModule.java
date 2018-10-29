package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesListContracts;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesListFragment;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LandlordsPropertiesListModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract LandlordsPropertiesListFragment landlordsPropertiesListFragment();

    @ActivityScoped
    @Binds
    abstract LandlordsPropertiesListContracts.Presenter landlordsPropertiesListPresenter(LandlordsPropertiesListPresenter landlordsPropertiesListPresenter);
}
