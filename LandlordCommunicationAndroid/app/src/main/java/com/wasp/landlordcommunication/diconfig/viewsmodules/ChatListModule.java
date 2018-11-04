package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.chat.ChatListContracts;
import com.wasp.landlordcommunication.views.chat.ChatListFragment;
import com.wasp.landlordcommunication.views.chat.ChatListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChatListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChatListFragment chatListFragment();

    @ActivityScoped
    @Binds
    abstract ChatListContracts.Presenter chatListPresenter(ChatListPresenter chatListPresenter);
}
