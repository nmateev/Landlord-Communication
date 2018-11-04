package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.HttpChatSessionsRepository;
import com.wasp.landlordcommunication.repositories.HttpPaymentsRepository;
import com.wasp.landlordcommunication.repositories.HttpPropertiesRepository;
import com.wasp.landlordcommunication.repositories.HttpRatingsRepository;
import com.wasp.landlordcommunication.repositories.HttpUsersRepository;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import com.wasp.landlordcommunication.repositories.cacherepostories.PropertiesCacheRepository;
import com.wasp.landlordcommunication.repositories.cacherepostories.UsersCacheRepository;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;
import com.wasp.landlordcommunication.repositories.base.RatingsRepository;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public UsersRepository usersRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<User> jsonParser) {

        String serverUrl = baseServerUrl + Constants.USERS_URL_SUFFIX;

        return new HttpUsersRepository(serverUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public CacheRepository<User> usersCacheRepository() {
        return new UsersCacheRepository();
    }


    @Provides
    @Singleton
    public RatingsRepository ratingsRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<Rating> jsonParser) {

        String serverUrl = baseServerUrl + Constants.RATINGS_URL_SUFFIX;

        return new HttpRatingsRepository(serverUrl, httpRequester, jsonParser);
    }

    @Provides
    @Singleton
    public PaymentsRepository paymentsRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<Payment> jsonParser) {

        String serverUrl = baseServerUrl + Constants.PAYMENTS_URL_SUFFIX;

        return new HttpPaymentsRepository(serverUrl, httpRequester, jsonParser);
    }


    @Provides
    @Singleton
    public CacheRepository<Property> propertiesCacheRepository() {
        return new PropertiesCacheRepository();
    }

    @Provides
    @Singleton
    public PropertiesRepository propertiesRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<Property> jsonParser) {

        String serverUrl = baseServerUrl + Constants.PROPERTIES_URL_SUFFIX;

        return new HttpPropertiesRepository(serverUrl, httpRequester, jsonParser);
    }


    @Provides
    @Singleton
    public ChatSessionsRepository chatSessionsRepository(@Named(Constants.BASE_SERVER_URL_VALUE_NAME) String baseServerUrl, HttpRequester httpRequester, JsonParser<ChatSession> jsonParser) {

        String serverUrl = baseServerUrl + Constants.CHAT_SESSIONS_URL_SUFFIX;

        return new HttpChatSessionsRepository(serverUrl, httpRequester, jsonParser);
    }
}