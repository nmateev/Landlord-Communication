package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.home.HomeActivityContracts;
import com.wasp.landlordcommunication.views.home.HomeActivityPresenter;
import com.wasp.landlordcommunication.views.home.HomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ActivityScoped
    @Binds
    abstract HomeActivityContracts.Presenter homeActivityPresenter(HomeActivityPresenter homeActivityPresenter);

}
