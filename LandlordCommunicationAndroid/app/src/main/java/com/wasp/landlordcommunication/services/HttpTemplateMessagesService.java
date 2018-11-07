package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.TemplateMessage;
import com.wasp.landlordcommunication.repositories.base.TemplateMessagesRepository;
import com.wasp.landlordcommunication.services.base.TemplateMessagesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpTemplateMessagesService implements TemplateMessagesService {

    private final TemplateMessagesRepository mTemplateMessagesRepository;

    public HttpTemplateMessagesService(TemplateMessagesRepository templateMessagesRepository) {
        mTemplateMessagesRepository = templateMessagesRepository;
    }

    @Override
    public List<String> getAllTemplateMessagesByType(String templateType) throws IOException {
        List<TemplateMessage> templateMessagesResult = mTemplateMessagesRepository.getAllTemplateMessagesByType(templateType);

        List<String> templateStrings = new ArrayList<>();
        for (TemplateMessage templateMessage : templateMessagesResult) {

            templateStrings.add(templateMessage.getTemplateText());
        }

        return templateStrings;
    }
}
