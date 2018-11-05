package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.chat.chatmessages.ChatContracts;
import com.wasp.landlordcommunication.views.chat.chatmessages.ChatFragment;
import com.wasp.landlordcommunication.views.chat.chatmessages.ChatPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChatModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChatFragment chatFragment();

    @ActivityScoped
    @Binds
    abstract ChatContracts.Presenter chatPresenter(ChatPresenter chatPresenter);
}
