package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpChatSessionsRepository implements ChatSessionsRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<ChatSession> mJsonParser;

    public HttpChatSessionsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<ChatSession> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public ChatSession createChatSessionBetweenTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {

        String requestBody = mJsonParser.toJson(chatSessionRequest);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public ChatSession getChatSessionByTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {
        String requestBody = mJsonParser.toJson(chatSessionRequest);

        String url = mServerUrl + Constants.SLASH_STRING_VALUE + Constants.CHAT_SESSIONS_RELATION_SUFFIX;
        String responseBody = mHttpRequester.post(url, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public boolean isChatSessionCreatedBetweenTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {
        String requestBody = mJsonParser.toJson(chatSessionRequest);
        String url = mServerUrl + Constants.SLASH_STRING_VALUE + Constants.CHAT_SESSIONS_CHECK_SUFFIX;

        String responseBody = mHttpRequester.post(url, requestBody);

        return Boolean.valueOf(responseBody);
    }

    @Override
    public List<ChatSession> getAllChatSessionsByUserTypeAndId(String userType, int id) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append(Constants.SLASH_STRING_VALUE)
                .append(userType.toLowerCase())
                .append(Constants.SLASH_STRING_VALUE)
                .append(id)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);

    }
}
