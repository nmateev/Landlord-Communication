package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.TemplateMessage;
import com.wasp.landlordcommunication.models.TemplateMessageDTO;
import com.wasp.landlordcommunication.services.TemplateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class TemplateMessagesRestController {
    @RestController
    @RequestMapping("/api/templateMessages")
    public class TemplateMessagesApiController {
        private TemplateMessageService templateMessagesService;

        @Autowired
        public TemplateMessagesApiController(TemplateMessageService templateMessageService){
            this.templateMessagesService=templateMessageService;
        }


        @GetMapping("{templateType}")
        List<TemplateMessageDTO> getByMessageType(@RequestParam String templateType) {
            List<TemplateMessage> templateMessages=templateMessagesService.getByMessageType(templateType);
            List<TemplateMessageDTO> result=new ArrayList<>();

            for (TemplateMessage message:templateMessages) {
                result.add(new TemplateMessageDTO(message.getText()));
            }

            return result;
        }



    }
}
