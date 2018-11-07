package com.wasp.landlordcommunication.services.base;

import java.io.IOException;
import java.util.List;

public interface TemplateMessagesService {

    List<String> getAllTemplateMessagesByType(String templateType) throws IOException;

}
