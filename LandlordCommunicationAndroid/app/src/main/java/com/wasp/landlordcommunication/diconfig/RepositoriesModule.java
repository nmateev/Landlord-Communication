package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.HttpRepository;
import com.wasp.landlordcommunication.repositories.PropertiesCacheRepository;
import com.wasp.landlordcommunication.repositories.UsersCacheRepository;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;
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

    @Provides
    @Singleton
    public CacheRepository<User> usersCacheRepository() {
        return new UsersCacheRepository();
    }

    @Provides
    @Singleton
    public Repository<Rating> ratingsRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<Rating> jsonParser) {

        String serverUrl = baseServerUrl + Constants.RATINGS_URL_SUFFIX;

        return new HttpRepository<>(serverUrl,
                Constants.RATINGS_CHECK_URL_SUFFIX,
                Constants.RATINGS_GET_BY_USERNAME_URL_SUFFIX,
                httpRequester,
                jsonParser);
    }

    @Provides
    @Singleton
    public Repository<Payment> paymentsRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<Payment> jsonParser) {

        String serverUrl = baseServerUrl + Constants.PAYMENTS_URL_SUFFIX;

        return new HttpRepository<>(serverUrl,
                Constants.EMPTY_STRING,
                Constants.PAYMENTS_GET_BY_ID_URL_SUFFIX,
                httpRequester,
                jsonParser);
    }


    @Provides
    @Singleton
    public CacheRepository<Property> propertiesCacheRepository() {
        return new PropertiesCacheRepository();
    }

    @Provides
    @Singleton
    public Repository<Property> propertiesRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<Property> jsonParser) {

        String serverUrl = baseServerUrl + Constants.PROPERTIES_URL_SUFFIX;

        return new HttpRepository<>(serverUrl,
                Constants.EMPTY_STRING,
                Constants.SLASH_STRING_VALUE,
                httpRequester,
                jsonParser);
    }
}