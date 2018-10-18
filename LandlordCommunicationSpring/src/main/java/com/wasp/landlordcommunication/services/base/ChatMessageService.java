package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.ChatMessage;

public interface ChatMessageService {
    ChatMessage getChatMessageById(int id);
    ChatMessage postChatMessage(ChatMessage newMessage);
}
