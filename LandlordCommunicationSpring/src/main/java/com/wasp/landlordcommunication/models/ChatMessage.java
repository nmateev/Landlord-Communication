package com.wasp.landlordcommunication.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = Constants.CHAT_MESSAGES_TABLE_NAME)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_MESSAGES_ID_COLUMN)
    private int messageId;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_TENANT_COLUMN)
    private int tenantId;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_LANDLORD_COLUMN)
    private int landlordId;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_SENDER_ID_COLUMN)
    private int senderId;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_CHAT_SESSION_ID_COLUMN)
    private int chatSessionId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_REPRESENTATION, timezone = Constants.TIME_ZONE_CONFIGURATION_VALUE)
    @Column(name = Constants.CHAT_MESSAGES_DATE_SENT_COLUMN)
    private Date dateSent;

    @NotNull
    @Size(min = Constants.TEXT_MESSAGE_MIN_LENGTH, max = Constants.TEXT_MESSAGE_MAX_LENGTH)
    @Column(name = Constants.CHAT_MESSAGES_TEXT_COLUMN)
    private String messageText;

    @Column(name = Constants.CHAT_MESSAGES_IMAGE_COLUMN)
    private String imageMessage;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_IS_DELIVERED_TO_TENANT_COLUMN)
    private boolean deliveredToTenant;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_IS_DELIVERED_TO_LANDLORD_COLUMN)
    private boolean deliveredToLandlord;


    public ChatMessage() {

    }

    public ChatMessage(int tenantId, int landlordId, int senderId, int chatSessionId, Date dateSent, String messageText, String imageMessage, boolean deliveredToTenant, boolean deliveredToLandlord) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setSenderId(senderId);
        setChatSessionId(chatSessionId);
        setDateSent(dateSent);
        setMessageText(messageText);
        setImageMessage(imageMessage);
        setDeliveredToTenant(deliveredToTenant);
        setDeliveredToLandlord(deliveredToLandlord);
    }


    public int getMessageId() {
        return messageId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getChatSessionId() {
        return chatSessionId;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getImageMessage() {
        return imageMessage;
    }

    public boolean getDeliveredToTenant() {
        return deliveredToTenant;
    }

    public boolean getDeliveredToLandlord() {
        return deliveredToLandlord;
    }

    public void setDeliveredToTenant(boolean deliveredToTenant) {
        this.deliveredToTenant = deliveredToTenant;
    }

    public void setDeliveredToLandlord(boolean deliveredToLandlord) {
        this.deliveredToLandlord = deliveredToLandlord;
    }

    private void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    private void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    private void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    private void setChatSessionId(int chatSessionId) {
        this.chatSessionId = chatSessionId;
    }

    private void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    private void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    private void setImageMessage(String imageMessage) {
        this.imageMessage = imageMessage;
    }

}
