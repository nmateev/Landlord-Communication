package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.templatemessage.TemplateMessage;

import java.util.List;

public interface TemplateMessagesRepository {

    List<TemplateMessage> getByTemplateType(String templateType);

}
