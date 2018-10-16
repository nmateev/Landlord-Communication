package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;

import java.util.List;

public interface TemplateMessageService {

    List<TemplateMessage> getByTemplateType(String templateType);

}
