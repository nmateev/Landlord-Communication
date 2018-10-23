package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.async.AsyncSchedulerProvider;
import com.wasp.landlordcommunication.async.base.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncModule {

    @Provides
    @Singleton
    public SchedulerProvider schedulerProvider() {
        return AsyncSchedulerProvider.getInstance();
    }
}
