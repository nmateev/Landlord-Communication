package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;

import java.io.IOException;

public class HttpChatSessionsService implements ChatSessionsService {

    private final ChatSessionsRepository mChatSessionsRepository;

    public HttpChatSessionsService(ChatSessionsRepository chatSessionsRepository) {
        mChatSessionsRepository = chatSessionsRepository;
    }

    @Override
    public ChatSession getChatSessionByTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {
        return null;
    }
}
