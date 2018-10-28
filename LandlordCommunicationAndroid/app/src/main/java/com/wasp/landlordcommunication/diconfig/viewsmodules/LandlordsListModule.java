package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.landlordslist.LandlordsListContracts;
import com.wasp.landlordcommunication.views.landlordslist.LandlordsListFragment;
import com.wasp.landlordcommunication.views.landlordslist.LandlordsListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LandlordsListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract LandlordsListFragment landlordsListFragment();

    @ActivityScoped
    @Binds
    abstract LandlordsListContracts.Presenter landlordsListPresenter(LandlordsListPresenter landlordsListPresenter);

}
