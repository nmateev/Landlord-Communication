package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.parsers.GsonJsonParser;
import com.wasp.landlordcommunication.parsers.base.JsonParser;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<User> songsJsonParser() {
        return new GsonJsonParser<>(User.class, User[].class);
    }

    @Provides
    public JsonParser<Rating> ratingsJsonParser() {
        return new GsonJsonParser<>(Rating.class, Rating[].class);
    }

    @Provides
    public JsonParser<Payment> paymentsJsonParser() {
        return new GsonJsonParser<>(Payment.class, Payment[].class);
    }

    @Provides
    public JsonParser<Property> propertiesJsonParser() {
        return new GsonJsonParser<>(Property.class, Property[].class);
    }

    @Provides
    public JsonParser<ChatSession> chatSessionsJsonParser() {
        return new GsonJsonParser<>(ChatSession.class, ChatSession[].class);
    }
}