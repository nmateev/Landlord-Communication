package com.wasp.landlordcommunication.models;

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
    @Column(name = Constants.CHAT_MESSAGES_CHAT_SESSION_ID_COLUMN)
    private int chatSessionId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
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
    private boolean isDeliveredToTenant;

    @NotNull
    @Column(name = Constants.CHAT_MESSAGES_IS_DELIVERED_TO_LANDLORD_COLUMN)
    private boolean isDeliveredToLandlord;


    public ChatMessage() {

    }

    public ChatMessage(int tenantId, int landlordId, int chatSessionId, Date dateSent, String messageText, String imageMessage, boolean isDeliveredToTenant, boolean isDeliveredToLandlord) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setChatSessionId(chatSessionId);
        setDateSent(dateSent);
        setMessageText(messageText);
        setImageMessage(imageMessage);
        setDeliveredToTenant(isDeliveredToTenant);
        setDeliveredToLandlord(isDeliveredToLandlord);
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

    public boolean isDeliveredToTenant() {
        return isDeliveredToTenant;
    }

    public boolean isDeliveredToLandlord() {
        return isDeliveredToLandlord;
    }

    public void setDeliveredToTenant(boolean deliveredToTenant) {
        isDeliveredToTenant = deliveredToTenant;
    }

    public void setDeliveredToLandlord(boolean deliveredToLandlord) {
        isDeliveredToLandlord = deliveredToLandlord;
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
