package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

import static com.wasp.landlordcommunication.utils.Constants.SLASH_STRING_VALUE;

public class HttpRepository<T> implements Repository<T> {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final String mPostUrlSuffix;
    private final String mByParameterUrlSuffix;
    private final JsonParser<T> mJsonParser;


    public HttpRepository(String serverUrl, String postUrlSuffix, String byParameterUrlSuffix, HttpRequester httpRequester, JsonParser<T> jsonParser) {
        mServerUrl = serverUrl;
        mPostUrlSuffix = postUrlSuffix;
        mByParameterUrlSuffix = byParameterUrlSuffix;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public T add(T item) throws IOException {

        String requestBody = mJsonParser.toJson(item);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public T delete(int id) throws IOException {
        String deleteServerUrl = mServerUrl + SLASH_STRING_VALUE + id;

        String responseBody = mHttpRequester.delete(deleteServerUrl);

        return mJsonParser.fromJson(responseBody);

    }

    @Override
    public T update(T item, int id) throws IOException {
        String updateServerUrl = mServerUrl + SLASH_STRING_VALUE + id;
        String requestBody = mJsonParser.toJson(item);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public T post(T item) throws IOException {
        String requestBody = mJsonParser.toJson(item);

        String postServerUrl = mServerUrl + mPostUrlSuffix;
        String responseBody = mHttpRequester.post(postServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }


    @Override
    public T getById(int id) throws IOException {

        String itemJson = mHttpRequester.get(mServerUrl + SLASH_STRING_VALUE + id);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public T getByParameter(String parameter) throws IOException {

        String itemJson = mHttpRequester.get(mServerUrl + mByParameterUrlSuffix + parameter);
        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public List<T> getAll() throws IOException {

        String itemsJson = mHttpRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public List<T> getAllByParameter(int id) throws IOException {

        String itemsJson = mHttpRequester.get(mServerUrl + SLASH_STRING_VALUE + id);

        return mJsonParser.fromJsonArray(itemsJson);
    }
}
