package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.TemplateMessage;
import com.wasp.landlordcommunication.repositories.TemplateMessageRepository;
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
    public List<TemplateMessage> getByMessageType(String templateType) {
        return repository.getByMessageType(templateType);
    }

}
