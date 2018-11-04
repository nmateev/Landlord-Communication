package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.ChatSession;

import java.io.IOException;
import java.util.List;

public interface ChatSessionsRepository {
    ChatSession getChatSessionByTenantAndLandlord(ChatSession chatSessionRequest) throws IOException;

    boolean isChatSessionCreatedBetweenTenantAndLandlord(ChatSession chatSessionRequest) throws IOException;

    List<ChatSession> getAllChatSessionsByUserTypeAndId(String userType, int id) throws IOException;
}
