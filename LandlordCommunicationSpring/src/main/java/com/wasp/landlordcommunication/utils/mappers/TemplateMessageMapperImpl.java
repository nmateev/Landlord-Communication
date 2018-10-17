package com.wasp.landlordcommunication.utils.mappers;

import com.wasp.landlordcommunication.utils.mappers.base.TemplateMessageMapper;
import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;
import com.wasp.landlordcommunication.models.templatemessages.TemplateMessageDTO;

import java.util.Objects;

public class TemplateMessageMapperImpl implements TemplateMessageMapper {


    @Override
    public TemplateMessageDTO mapToTemplateMessageDTO(TemplateMessage templateMessageToMap) {
        TemplateMessageDTO templateMessageDTO = new TemplateMessageDTO();

        if (!Objects.equals(templateMessageToMap, null)) {

            templateMessageDTO = new TemplateMessageDTO(templateMessageToMap.getTemplateText());
        }
        return templateMessageDTO;
    }
}
