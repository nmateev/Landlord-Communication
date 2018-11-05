package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;

public class HttpChatMessagesRepository implements ChatMessagesRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<ChatMessage> mJsonParser;

    public HttpChatMessagesRepository(String serverUrl, HttpRequester httpRequester, JsonParser<ChatMessage> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }
}
