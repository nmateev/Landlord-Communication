package com.wasp.landlordcommunication.models;

import java.util.Date;

public class ChatMessage {


    private int messageId;
    private int tenantId;
    private int landlordId;
    private int chatSessionId;
    private Date dateSent;
    private String messageText;
    private String imageMessage;
    private boolean isDeliveredToTenant;
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
        this.isDeliveredToTenant = deliveredToTenant;
    }

    public void setDeliveredToLandlord(boolean deliveredToLandlord) {
        this.isDeliveredToLandlord = deliveredToLandlord;
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
