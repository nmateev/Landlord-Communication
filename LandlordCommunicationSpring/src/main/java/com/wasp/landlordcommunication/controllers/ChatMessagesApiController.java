package com.wasp.landlordcommunication.controllers;


import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.services.base.ChatMessagesService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Constants.CHAT_MESSAGES_ROOT_MAPPING)
public class ChatMessagesApiController {

    private final ChatMessagesService chatMessagesService;

    @Autowired
    public ChatMessagesApiController(ChatMessagesService chatMessagesService) {
        this.chatMessagesService = chatMessagesService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<ChatMessage> getMessagesByChatSessionId(@PathVariable int id) {
        return chatMessagesService.getMessagesByChatSessionId(id);
    }

    @RequestMapping(value = "/tenant/{id}", method = RequestMethod.GET)
    public List<ChatMessage> getTenantsUndeliveredMessagesByChatSessionId(@PathVariable int id) {

        return chatMessagesService.getTenantsUndeliveredMessagesByChatSessionId(id);
    }

    @RequestMapping(value = "/landlord/{id}", method = RequestMethod.GET)
    public List<ChatMessage> getLandlordsUndeliveredMessagesByChatSessionId(@PathVariable int id) {
        return chatMessagesService.getLandlordsUndeliveredMessagesByChatSessionId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ChatMessage postChatMessage(@RequestBody @Valid ChatMessage newMessage) {
        return chatMessagesService.postChatMessage(newMessage);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ChatMessage updateChatMessage(@RequestBody @Valid ChatMessage messageToUpdate) {
        return chatMessagesService.updateChatMessage(messageToUpdate);
    }

}
