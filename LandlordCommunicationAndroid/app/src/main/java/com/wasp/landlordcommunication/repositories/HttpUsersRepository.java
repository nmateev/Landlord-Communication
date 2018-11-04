package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpUsersRepository implements UsersRepository {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<User> mJsonParser;

    public HttpUsersRepository(String serverUrl, HttpRequester httpRequester, JsonParser<User> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public User getUserByUserName(String name) throws IOException {
        String url = mServerUrl + Constants.USERS_GET_BY_USERNAME_URL_SUFFIX + name;
        String itemJson = mHttpRequester.get(url);

        return mJsonParser.fromJson(itemJson);
    }

    @Override
    public User loginUser(User user) throws IOException {

        String requestBody = mJsonParser.toJson(user);

        String url = mServerUrl + Constants.USERS_LOGIN_URL_SUFFIX;
        String responseBody = mHttpRequester.post(url, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public User createUser(User userToCreate) throws IOException {
        String requestBody = mJsonParser.toJson(userToCreate);

        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public User updateUser(User user, int userId) throws IOException {

        String updateServerUrl = mServerUrl + Constants.SLASH_STRING_VALUE + userId;
        String requestBody = mJsonParser.toJson(user);

        String responseBody = mHttpRequester.update(updateServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<User> getAllUsersByType(String type) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append(Constants.SLASH_STRING_VALUE)
                .append(Constants.USERS_TYPE_SUFFIX)
                .append(Constants.SLASH_STRING_VALUE)
                .append(type)
                .toString();
        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }
}
