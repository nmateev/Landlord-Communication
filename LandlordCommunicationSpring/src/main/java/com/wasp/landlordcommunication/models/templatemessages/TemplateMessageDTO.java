package com.wasp.landlordcommunication.models.templatemessages;

public class TemplateMessageDTO {
    private String templateText;

    public TemplateMessageDTO() {

    }

    public TemplateMessageDTO(String templateText) {
        setTemplateText(templateText);
    }

    public String getTemplateText() {
        return templateText;
    }

    private void setTemplateText(String templateText) {
        this.templateText = templateText;
    }
}
