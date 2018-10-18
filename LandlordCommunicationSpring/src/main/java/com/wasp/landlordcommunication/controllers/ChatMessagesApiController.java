package com.wasp.landlordcommunication.controllers;


import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.services.base.ChatMessageService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.CHAT_MESSAGES_ROOT_MAPPING)
public class ChatMessagesApiController {

    private ChatMessageService chatMessageService;

    @Autowired
    public ChatMessagesApiController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ChatMessage getChatMessageById(@PathVariable int id) {
        return chatMessageService.getChatMessageById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ChatMessage postChatMessage(@RequestBody ChatMessage newMessage) {
        return chatMessageService.postChatMessage(newMessage);
    }
}
