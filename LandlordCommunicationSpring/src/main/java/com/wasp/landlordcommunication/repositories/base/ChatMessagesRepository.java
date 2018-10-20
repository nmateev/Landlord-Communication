package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.ChatMessage;

import java.util.List;

public interface ChatMessagesRepository {

    List<ChatMessage> getMessagesByChatSessionId(int id);

    List<ChatMessage> getTenantsUndeliveredMessagesByChatSessionId(int id);

    List<ChatMessage> getLandlordsUndeliveredMessagesByChatSessionId(int id);

    ChatMessage postChatMessage(ChatMessage message);

    ChatMessage updateChatMessage(ChatMessage messageToUpdate);
}
