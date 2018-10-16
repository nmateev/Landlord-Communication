package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;
import com.wasp.landlordcommunication.repositories.base.TemplateMessageRepository;
import com.wasp.landlordcommunication.services.base.TemplateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateMessageServiceImpl implements TemplateMessageService {
    private TemplateMessageRepository repository;

    @Autowired
    public TemplateMessageServiceImpl(TemplateMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TemplateMessage> getByTemplateType(String templateType) {
        return repository.getByTemplateType(templateType);
    }
}
