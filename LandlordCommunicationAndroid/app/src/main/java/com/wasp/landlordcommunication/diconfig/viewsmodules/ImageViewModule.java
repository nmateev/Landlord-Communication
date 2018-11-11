package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.chat.image.ImageViewContracts;
import com.wasp.landlordcommunication.views.chat.image.ImageViewFragment;
import com.wasp.landlordcommunication.views.chat.image.ImageViewPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ImageViewModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ImageViewFragment imageViewFragment();

    @ActivityScoped
    @Binds
    abstract ImageViewContracts.Presenter imageViewPresenter(ImageViewPresenter imageViewPresenter);
}
