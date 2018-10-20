package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.chatsession.ChatSessionDTO;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.CHAT_SESSIONS_ROOT_MAPPING)
public class ChatSessionsApiController {

    private final ChatSessionsService chatSessionsService;

    @Autowired
    public ChatSessionsApiController(ChatSessionsService chatSessionsService) {
        this.chatSessionsService = chatSessionsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    ChatSession createChatSession(@RequestBody ChatSession newChatSession) {
        return chatSessionsService.createChatSession(newChatSession);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    boolean isChatSessionCreated(@RequestBody ChatSessionDTO chatSessionDTO) {
        return chatSessionsService.isChatSessionCreated(chatSessionDTO);
    }

    @RequestMapping(value = "/relation", method = RequestMethod.POST)
    ChatSession getChatSessionByTenantAndLandlord(@RequestBody ChatSessionDTO chatSessionDTO) {
        return chatSessionsService.getChatSessionByTenantAndLandlord(chatSessionDTO);
    }


    @RequestMapping(value = "/tenant/{tenantId}", method = RequestMethod.GET)
    List<ChatSession> getAllChatSessionsByTenant(@PathVariable int tenantId) {
        return chatSessionsService.getAllChatSessionsByTenant(tenantId);
    }

    @RequestMapping(value = "/landlord/{landlordId}", method = RequestMethod.GET)
    List<ChatSession> getAllChatSessionsByLandlord(@PathVariable int landlordId) {
        return chatSessionsService.getAllChatSessionsByLandlord(landlordId);
    }
}
