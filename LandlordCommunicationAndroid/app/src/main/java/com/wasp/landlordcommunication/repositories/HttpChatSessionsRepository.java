package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;

public class HttpChatSessionsRepository implements ChatSessionsRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<ChatSession> mJsonParser;

    public HttpChatSessionsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<ChatSession> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


}
