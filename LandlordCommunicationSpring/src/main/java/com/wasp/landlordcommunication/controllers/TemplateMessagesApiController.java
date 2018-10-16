package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.mappers.base.TemplateMessageMapper;
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
    private TemplateMessageMapper templateMessageMapper;

    @Autowired
    public TemplateMessagesApiController(TemplateMessageService templateMessageService, TemplateMessageMapper templateMessageMapper) {
        this.templateMessagesService = templateMessageService;
        this.templateMessageMapper = templateMessageMapper;
    }


    @RequestMapping(method = RequestMethod.GET)
    List<TemplateMessageDTO> getByMessageType(@RequestParam String templateType) {

        return templateMessagesService
                .getByTemplateType(templateType)
                .stream()
                .map(templateMessage ->
                        templateMessageMapper.mapToTemplateMessageDTO(templateMessage))
                .collect(Collectors.toList());

    }
}

