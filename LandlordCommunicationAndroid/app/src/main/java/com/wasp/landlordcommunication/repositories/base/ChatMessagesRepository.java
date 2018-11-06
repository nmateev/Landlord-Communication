package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.ChatMessage;

import java.io.IOException;
import java.util.List;

public interface ChatMessagesRepository {

    List<ChatMessage> getChatMessagesByChatSessionId(int chatSessionId) throws IOException;

    ChatMessage updateDeliveredStatusForChatMessage(ChatMessage chatMessage) throws IOException;

    List<ChatMessage> getUndeliveredMessagesByChatSessionIdAndUserType(int chatSessionId, String userType) throws IOException;

    ChatMessage postChatMessage(ChatMessage newChatMessage) throws IOException;
}
