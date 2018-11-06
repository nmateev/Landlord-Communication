package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;
import com.wasp.landlordcommunication.services.base.ChatMessagesService;

import java.io.IOException;
import java.util.List;

public class HttpChatMessagesService implements ChatMessagesService {

    private final ChatMessagesRepository mChatMessagesRepository;

    public HttpChatMessagesService(ChatMessagesRepository chatMessagesRepository) {
        mChatMessagesRepository = chatMessagesRepository;
    }

    @Override
    public List<ChatMessage> getChatMessagesByChatSessionId(int chatSessionId) throws IOException {
        return mChatMessagesRepository.getChatMessagesByChatSessionId(chatSessionId);
    }

    @Override
    public ChatMessage updateDeliveredStatusForChatMessage(ChatMessage chatMessage) throws IOException {
        return mChatMessagesRepository.updateDeliveredStatusForChatMessage(chatMessage);
    }

    @Override
    public List<ChatMessage> getUndeliveredMessagesByChatSessionIdAndUserType(int chatSessionId, String userType) throws IOException {
        return mChatMessagesRepository.getUndeliveredMessagesByChatSessionIdAndUserType(chatSessionId, userType);
    }

    @Override
    public ChatMessage postChatMessage(ChatMessage newChatMessage) throws IOException {
        return mChatMessagesRepository.postChatMessage(newChatMessage);
    }
}
