package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.landlordpropertydetails.LandlordPropertyDetailsContracts;
import com.wasp.landlordcommunication.views.landlordpropertydetails.LandlordPropertyDetailsFragment;
import com.wasp.landlordcommunication.views.landlordpropertydetails.LandlordPropertyDetailsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LandlordPropertyDetailsModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract LandlordPropertyDetailsFragment landlordPropertyDetailsFragment();

    @ActivityScoped
    @Binds
    abstract LandlordPropertyDetailsContracts.Presenter landlordPropertyDetailsPresenter(LandlordPropertyDetailsPresenter landlordPropertyDetailsPresenter);
}
