package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.TemplateMessage;

import java.util.List;

public interface TemplateMessageService {
    List<TemplateMessage> getByMessageType(String templateType);
}
