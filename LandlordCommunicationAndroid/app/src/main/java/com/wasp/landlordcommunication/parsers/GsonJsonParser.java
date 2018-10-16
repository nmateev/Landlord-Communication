package com.wasp.landlordcommunication.parsers;

import com.google.gson.Gson;
import com.wasp.landlordcommunication.parsers.base.JsonParser;

import java.util.Arrays;
import java.util.List;

public class GsonJsonParser<T> implements JsonParser<T> {


    private final Class<T> mklass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;

    public GsonJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        mklass = klass;
        mArrayKlass = arrayKlass;
        mGson = new Gson();
    }


    @Override
    public T fromJson(String jsonString) {
        return mGson.fromJson(jsonString, mklass);
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