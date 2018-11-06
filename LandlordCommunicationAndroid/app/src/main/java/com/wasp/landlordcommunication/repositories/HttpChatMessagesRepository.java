package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpChatMessagesRepository implements ChatMessagesRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<ChatMessage> mJsonParser;

    public HttpChatMessagesRepository(String serverUrl, HttpRequester httpRequester, JsonParser<ChatMessage> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public List<ChatMessage> getChatMessagesByChatSessionId(int chatSessionId) throws IOException {
        String url = new StringBuilder()
                .append(mServerUrl)
                .append(Constants.SLASH_STRING_VALUE)
                .append(chatSessionId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);

    }

    @Override
    public ChatMessage updateDeliveredStatusForChatMessage(ChatMessage chatMessage) throws IOException {
        String requestBody = mJsonParser.toJson(chatMessage);
        String responseBody = mHttpRequester.update(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<ChatMessage> getUndeliveredMessagesByChatSessionIdAndUserType(int chatSessionId, String userType) throws IOException {

        String url = new StringBuilder()
                .append(mServerUrl)
                .append(Constants.SLASH_STRING_VALUE)
                .append(userType.toLowerCase())
                .append(Constants.SLASH_STRING_VALUE)
                .append(chatSessionId)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);
    }

    @Override
    public ChatMessage postChatMessage(ChatMessage newChatMessage) throws IOException {

        String requestBody = mJsonParser.toJson(newChatMessage);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }
}
