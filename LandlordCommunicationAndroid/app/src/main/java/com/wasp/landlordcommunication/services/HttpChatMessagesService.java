package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;
import com.wasp.landlordcommunication.services.base.ChatMessagesService;

public class HttpChatMessagesService implements ChatMessagesService {

    private final ChatMessagesRepository mChatMessagesRepository;

    public HttpChatMessagesService(ChatMessagesRepository chatMessagesRepository) {
        mChatMessagesRepository = chatMessagesRepository;
    }
}
