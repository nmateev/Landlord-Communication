package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.chatsession.ChatSessionDTO;

import java.util.List;

public interface ChatSessionsRepository {

    ChatSession createChatSession(ChatSession newChatSession);

    ChatSession getChatSessionById(int chatSessionId);

    ChatSession getChatSessionByTenantAndLandlord(ChatSessionDTO chatSessionDTO);

    boolean isChatSessionCreated(ChatSessionDTO chatSessionDTO);

    List<ChatSession> getAllChatSessionsByTenant(int tenantId);

    List<ChatSession> getAllChatSessionsByLandlord(int landlordId);

}
