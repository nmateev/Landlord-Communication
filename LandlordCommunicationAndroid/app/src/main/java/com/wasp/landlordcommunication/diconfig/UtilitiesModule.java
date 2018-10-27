package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilitiesModule {

    @Provides
    public ImageEncoder imageEncoder() {
        return new com.wasp.landlordcommunication.utils.ImageEncoder();
    }
}
