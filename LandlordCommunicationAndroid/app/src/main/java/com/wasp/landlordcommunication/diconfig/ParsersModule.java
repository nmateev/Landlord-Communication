package com.wasp.landlordcommunication.diconfig;

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
}