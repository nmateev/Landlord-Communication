package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.templatemessages.TemplateMessageDTO;
import com.wasp.landlordcommunication.services.base.TemplateMessageService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.TEMPLATE_MESSAGES_ROOT_MAPPING)
public class TemplateMessagesApiController {

    private TemplateMessageService templateMessagesService;

    @Autowired
    public TemplateMessagesApiController(TemplateMessageService templateMessageService) {
        this.templateMessagesService = templateMessageService;
    }


    @RequestMapping(method = RequestMethod.GET)
    List<TemplateMessageDTO> getByMessageType(@RequestParam String templateType) {

        return templateMessagesService
                .getByTemplateType(templateType)
                .stream()
                .map(templateMessage ->
                        new TemplateMessageDTO(templateMessage.getTemplateText()))
                .collect(Collectors.toList());

    }
}

