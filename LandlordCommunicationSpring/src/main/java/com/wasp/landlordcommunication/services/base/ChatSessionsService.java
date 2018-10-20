package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.chatsession.ChatSessionDTO;

import java.util.List;

public interface ChatSessionsService {

    ChatSession createChatSession(ChatSession newChatSession);

    ChatSession getChatSessionByTenantAndLandlord(ChatSessionDTO chatSessionDTO);

    boolean isChatSessionCreated(ChatSessionDTO chatSessionDTO);

    List<ChatSession> getAllChatSessionsByTenant(int tenantId);

    List<ChatSession> getAllChatSessionsByLandlord(int landlordId);
}
