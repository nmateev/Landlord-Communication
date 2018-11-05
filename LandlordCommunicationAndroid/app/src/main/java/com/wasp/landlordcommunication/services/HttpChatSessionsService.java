package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;

import java.io.IOException;
import java.util.List;

public class HttpChatSessionsService implements ChatSessionsService {

    private final ChatSessionsRepository mChatSessionsRepository;

    public HttpChatSessionsService(ChatSessionsRepository chatSessionsRepository) {
        mChatSessionsRepository = chatSessionsRepository;
    }

    @Override
    public ChatSession createChatSessionBetweenTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {
        return mChatSessionsRepository.createChatSessionBetweenTenantAndLandlord(chatSessionRequest);
    }

    @Override
    public ChatSession getChatSessionByTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {
        return mChatSessionsRepository.getChatSessionByTenantAndLandlord(chatSessionRequest);
    }

    @Override
    public boolean isChatSessionCreatedBetweenTenantAndLandlord(ChatSession chatSessionRequest) throws IOException {

        return mChatSessionsRepository.isChatSessionCreatedBetweenTenantAndLandlord(chatSessionRequest);
    }

    @Override
    public List<ChatSession> getAllChatSessionsByUserTypeAndId(String userType, int id) throws IOException {
        return mChatSessionsRepository.getAllChatSessionsByUserTypeAndId(userType,id);
    }
}
