package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;
import com.wasp.landlordcommunication.services.base.ChatMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessagesServiceImpl implements ChatMessagesService {

    private final ChatMessagesRepository chatMessagesRepository;

    @Autowired
    public ChatMessagesServiceImpl(ChatMessagesRepository chatMessagesRepository) {
        this.chatMessagesRepository = chatMessagesRepository;
    }

    @Override
    public List<ChatMessage> getMessagesByChatSessionId(int id) {
        return chatMessagesRepository.getMessagesByChatSessionId(id);
    }

    @Override
    public List<ChatMessage> getTenantsUndeliveredMessagesByChatSessionId(int id) {
        return chatMessagesRepository.getTenantsUndeliveredMessagesByChatSessionId(id);
    }

    @Override
    public List<ChatMessage> getLandlordsUndeliveredMessagesByChatSessionId(int id) {
        return chatMessagesRepository.getLandlordsUndeliveredMessagesByChatSessionId(id);
    }

    @Override
    public ChatMessage postChatMessage(ChatMessage newMessage) {
        return chatMessagesRepository.postChatMessage(newMessage);
    }

    @Override
    public ChatMessage updateChatMessage(ChatMessage messageToUpdate) {
        return chatMessagesRepository.updateChatMessage(messageToUpdate);
    }
}
