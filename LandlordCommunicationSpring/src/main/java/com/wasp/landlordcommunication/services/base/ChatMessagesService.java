package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.ChatMessage;

import java.util.List;

public interface ChatMessagesService {

    List<ChatMessage> getMessagesByChatSessionId(int id);

    List<ChatMessage> getTenantsUndeliveredMessagesByChatSessionId(int id);

    List<ChatMessage> getLandlordsUndeliveredMessagesByChatSessionId(int id);

    ChatMessage postChatMessage(ChatMessage newMessage);

    ChatMessage updateChatMessage(ChatMessage messageToUpdate);

}
