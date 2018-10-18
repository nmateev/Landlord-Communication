package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.ChatMessage;

public interface ChatMessageRepository {
    ChatMessage getChatMessageById(int id);
    ChatMessage postChatMessage(ChatMessage message);
}
