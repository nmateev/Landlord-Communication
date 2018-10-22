package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.templatemessage.TemplateMessage;
import com.wasp.landlordcommunication.repositories.base.TemplateMessagesRepository;
import com.wasp.landlordcommunication.services.base.TemplateMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateMessagesServiceImpl implements TemplateMessagesService {
    private final TemplateMessagesRepository repository;

    @Autowired
    public TemplateMessagesServiceImpl(TemplateMessagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TemplateMessage> getByTemplateType(String templateType) {
        return repository.getByTemplateType(templateType);
    }
}
