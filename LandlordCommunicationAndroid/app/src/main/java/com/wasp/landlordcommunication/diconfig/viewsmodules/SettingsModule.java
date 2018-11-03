package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.settings.SettingsContracts;
import com.wasp.landlordcommunication.views.settings.SettingsFragment;
import com.wasp.landlordcommunication.views.settings.SettingsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SettingsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SettingsFragment settingsFragment();

    @ActivityScoped
    @Binds
    abstract SettingsContracts.Presenter settingsPresenter(SettingsPresenter settingsPresenter);
}
