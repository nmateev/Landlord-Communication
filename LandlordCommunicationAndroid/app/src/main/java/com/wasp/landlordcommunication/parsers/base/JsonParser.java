package com.wasp.landlordcommunication.parsers.base;

import java.util.List;

public interface JsonParser<T> {


    T fromJson(String jsonString);

    String toJson(T object);

    List<T> fromJsonArray(String jsonArrayString);
}