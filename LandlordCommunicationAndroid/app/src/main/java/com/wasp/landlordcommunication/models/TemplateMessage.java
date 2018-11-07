package com.wasp.landlordcommunication.models;

public class TemplateMessage {


    private int templateMessageId;
    private String templateText;
    private String templateType;

    public TemplateMessage() {

    }

    public TemplateMessage(String templateText, String templateType) {
        setTemplateText(templateText);
        setTemplateType(templateType);
    }

    public int getTemplateMessageId() {
        return templateMessageId;
    }

    public String getTemplateText() {
        return templateText;
    }

    public String getTemplateType() {
        return templateType;
    }

    private void setTemplateMessageId(int templateMessageId) {
        this.templateMessageId = templateMessageId;
    }

    private void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    private void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
}
