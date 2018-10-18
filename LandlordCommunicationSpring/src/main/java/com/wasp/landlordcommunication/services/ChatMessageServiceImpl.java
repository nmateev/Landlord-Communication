package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.repositories.base.ChatMessageRepository;
import com.wasp.landlordcommunication.services.base.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    private ChatMessageRepository repository;

    @Autowired
    public ChatMessageServiceImpl(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatMessage getChatMessageById(int id) {
        return repository.getChatMessageById(id);
    }

    @Override
    public ChatMessage postChatMessage(ChatMessage newMessage) {
        return repository.postChatMessage(newMessage);
    }
}
