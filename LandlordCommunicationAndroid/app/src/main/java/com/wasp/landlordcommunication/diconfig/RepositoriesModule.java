package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.HttpRepository;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public Repository<User> usersRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<User> jsonParser) {

        String serverUrl = baseServerUrl + Constants.USERS_URL_SUFFIX;

        return new HttpRepository<>(serverUrl,
                Constants.USERS_LOGIN_URL_SUFFIX,
                Constants.USERS_GET_BY_USERNAME_URL_SUFFIX,
                httpRequester,
                jsonParser);
    }
}