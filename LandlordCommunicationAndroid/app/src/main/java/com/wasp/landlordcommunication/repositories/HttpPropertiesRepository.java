package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpPropertiesRepository implements PropertiesRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Property> mJsonParser;

    public HttpPropertiesRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Property> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public List<Property> getUsersPropertiesByIdAndType(int userId, String userType) throws IOException {

        String url = new StringBuilder()
                .append(mServerUrl)
                .append(Constants.SLASH_STRING_VALUE)
                .append(userType.toLowerCase())
                .append(Constants.SLASH_STRING_VALUE)
                .append(userId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);

    }

    @Override
    public Property getPropertyById(int propertyId) throws IOException {
        String url = mServerUrl + Constants.SLASH_STRING_VALUE + propertyId;
        String itemJson = mHttpRequester.get(url);

        return mJsonParser.fromJson(itemJson);

    }

    @Override
    public Property updateProperty(Property property, int propertyId) throws IOException {

        String url = mServerUrl + Constants.SLASH_STRING_VALUE + propertyId;
        String requestBody = mJsonParser.toJson(property);

        String responseBody = mHttpRequester.update(url, requestBody);

        return mJsonParser.fromJson(responseBody);

    }
}
