package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.TemplateMessage;

import java.io.IOException;
import java.util.List;

public interface TemplateMessagesRepository {


    List<TemplateMessage> getAllTemplateMessagesByType(String templateType) throws IOException;
}
