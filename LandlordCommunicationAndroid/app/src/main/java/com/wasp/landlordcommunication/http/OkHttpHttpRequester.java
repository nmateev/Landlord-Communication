package com.wasp.landlordcommunication.http;

import com.wasp.landlordcommunication.http.base.HttpRequester;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHttpRequester implements HttpRequester {

    private final OkHttpClient mHttpClient;

    public OkHttpHttpRequester() {

        mHttpClient = new OkHttpClient();
    }

    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = mHttpClient
                .newCall(request)
                .execute();

        return response
                .body()
                .string();
    }

    @Override
    public String post(String url, String body) throws IOException {

        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json"), body);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = mHttpClient
                .newCall(request)
                .execute();
        return response
                .body()
                .string();
    }

    @Override
    public String update(String url, String body) throws IOException {
        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json"), body);

        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();

        Response response = mHttpClient
                .newCall(request)
                .execute();
        return response
                .body()
                .string();
    }

    @Override
    public String delete(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        Response response = mHttpClient
                .newCall(request)
                .execute();

        return response
                .body()
                .string();
    }
}
