package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.http.OkHttpHttpRequester;
import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named(Constants.BASE_SERVER_URL_VALUE_NAME)
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}
