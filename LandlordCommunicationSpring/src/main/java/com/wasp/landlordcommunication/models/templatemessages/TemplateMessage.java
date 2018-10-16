package com.wasp.landlordcommunication.models.templatemessages;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.TEMPLATE_MESSAGES_TABLE_NAME)
public class TemplateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.TEMPLATE_MESSAGES_ID_COLUMN_NAME)
    private int templateMessageId;

    @Column(name = Constants.TEMPLATE_MESSAGES_TEXT_COLUMN_NAME)
    private String templateText;


    @Column(name = Constants.TEMPLATE_MESSAGES_TYPE_COLUMN_NAME)
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
