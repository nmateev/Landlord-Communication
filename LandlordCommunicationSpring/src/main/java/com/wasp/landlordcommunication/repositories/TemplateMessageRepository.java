package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.TemplateMessage;

import java.util.List;

public interface TemplateMessageRepository {
    List<TemplateMessage> getByMessageType(String templateType);

}
