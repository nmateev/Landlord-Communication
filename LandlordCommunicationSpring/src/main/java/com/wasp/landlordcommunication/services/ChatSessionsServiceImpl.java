package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.chatsession.ChatSessionDTO;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatSessionsServiceImpl implements ChatSessionsService {

    private final ChatSessionsRepository chatSessionsRepository;

    @Autowired
    public ChatSessionsServiceImpl(ChatSessionsRepository chatSessionsRepository) {
        this.chatSessionsRepository = chatSessionsRepository;
    }

    @Override
    public ChatSession createChatSession(ChatSession newChatSession) {
        return chatSessionsRepository.createChatSession(newChatSession);
    }

    @Override
    public ChatSession getChatSessionByTenantAndLandlord(ChatSessionDTO chatSessionDTO) {
        return chatSessionsRepository.getChatSessionByTenantAndLandlord(chatSessionDTO);
    }

    @Override
    public boolean isChatSessionCreated(ChatSessionDTO chatSessionDTO) {
        return chatSessionsRepository.isChatSessionCreated(chatSessionDTO);
    }

    @Override
    public List<ChatSession> getAllChatSessionsByTenant(int tenantId) {
        return chatSessionsRepository.getAllChatSessionsByTenant(tenantId);
    }

    @Override
    public List<ChatSession> getAllChatSessionsByLandlord(int landlordId) {
        return chatSessionsRepository.getAllChatSessionsByLandlord(landlordId);
    }
}
