package com.wasp.landlordcommunication.models.mappers.base;

import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;
import com.wasp.landlordcommunication.models.templatemessages.TemplateMessageDTO;

public interface TemplateMessageMapper {
    TemplateMessageDTO mapToTemplateMessageDTO(TemplateMessage templateMessageToMap);
}
