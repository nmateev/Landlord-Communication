package com.wasp.landlordcommunication.models;

public class TemplateMessageDTO {
    private String text;

    public TemplateMessageDTO(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
