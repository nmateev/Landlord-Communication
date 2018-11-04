package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.ChatSession;

import java.io.IOException;

public interface ChatSessionsService {

    ChatSession getChatSessionByTenantAndLandlord(ChatSession chatSessionRequest) throws IOException;

}
