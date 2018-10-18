package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.utils.mappers.base.TemplateMessageMapper;
import com.wasp.landlordcommunication.models.templatemessages.TemplateMessageDTO;
import com.wasp.landlordcommunication.services.base.TemplateMessagesService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.TEMPLATE_MESSAGES_ROOT_MAPPING)
public class TemplateMessagesApiController {

    private final TemplateMessagesService templateMessagesService;
    private final TemplateMessageMapper templateMessageMapper;

    @Autowired
    public TemplateMessagesApiController(TemplateMessagesService templateMessagesService, TemplateMessageMapper templateMessageMapper) {
        this.templateMessagesService = templateMessagesService;
        this.templateMessageMapper = templateMessageMapper;
    }


    @RequestMapping(value = "/{templateType}", method = RequestMethod.GET)
    List<TemplateMessageDTO> getByMessageType(@PathVariable String templateType) {

        return templateMessagesService
                .getByTemplateType(templateType)
                .stream()
                .map(templateMessageMapper::mapToTemplateMessageDTO)
                .collect(Collectors.toList());

    }
}

