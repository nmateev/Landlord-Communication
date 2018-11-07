package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.TemplateMessage;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.TemplateMessagesRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpTemplateMessagesRepository implements TemplateMessagesRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<TemplateMessage> mJsonParser;

    public HttpTemplateMessagesRepository(String serverUrl, HttpRequester httpRequester, JsonParser<TemplateMessage> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public List<TemplateMessage> getAllTemplateMessagesByType(String templateType) throws IOException {

        String url = mServerUrl + Constants.SLASH_STRING_VALUE + templateType;
        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }
}
