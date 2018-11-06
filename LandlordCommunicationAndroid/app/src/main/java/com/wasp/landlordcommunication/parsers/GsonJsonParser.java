package com.wasp.landlordcommunication.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser<T> {


    private final Class<T> mKlass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;

    public GsonJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        mKlass = klass;
        mArrayKlass = arrayKlass;
        mGson = new GsonBuilder()
                .setDateFormat(Constants.DATE_REPRESENTATION)
                .create();
    }


    @Override
    public T fromJson(String jsonString) {
        return mGson.fromJson(jsonString, mKlass);
    }

    @Override
    public String toJson(T object) {
        return mGson.toJson(object);
    }

    @Override
    public List<T> fromJsonArray(String jsonArrayString) {
        T[] result = mGson.fromJson(jsonArrayString, mArrayKlass);

        return Arrays.asList(result);
    }
}