package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;

import java.util.List;

public interface TemplateMessageRepository {

    List<TemplateMessage> getByTemplateType(String templateType);

}
