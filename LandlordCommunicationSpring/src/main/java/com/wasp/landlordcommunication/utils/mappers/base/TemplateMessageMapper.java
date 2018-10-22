package com.wasp.landlordcommunication.utils.mappers.base;

import com.wasp.landlordcommunication.models.templatemessage.TemplateMessage;
import com.wasp.landlordcommunication.models.templatemessage.TemplateMessageDTO;

public interface TemplateMessageMapper {
    TemplateMessageDTO mapToTemplateMessageDTO(TemplateMessage templateMessageToMap);
}
