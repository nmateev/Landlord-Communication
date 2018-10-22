package com.wasp.landlordcommunication.models.templatemessage;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = Constants.TEMPLATE_MESSAGES_TABLE_NAME)
public class TemplateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.TEMPLATE_MESSAGES_ID_COLUMN_NAME)
    private int templateMessageId;

    @NotNull
    @Size(min = Constants.STRING_VALIDATION_MIN_TEXT, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.TEMPLATE_MESSAGES_TEXT_COLUMN_NAME)
    private String templateText;

    @NotNull
    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.TEXT_VALIDATION_MAX_VALUE)
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
