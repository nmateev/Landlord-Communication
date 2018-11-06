package com.wasp.landlordcommunication.models;

import java.util.Date;

public class ChatMessage {


    private int messageId;
    private int tenantId;
    private int landlordId;
    private int senderId;
    private int chatSessionId;
    private String dateSent;
    private String messageText;
    private String imageMessage;
    private boolean isDeliveredToTenant;
    private boolean isDeliveredToLandlord;


    public ChatMessage() {

    }

    public ChatMessage(int tenantId, int landlordId, int senderId, int chatSessionId, String dateSent, String messageText, String imageMessage, boolean isDeliveredToTenant, boolean isDeliveredToLandlord) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setSenderId(senderId);
        setChatSessionId(chatSessionId);
        setDateSent(dateSent);
        setMessageText(messageText);
        setImageMessage(imageMessage);
        setIsDeliveredToTenant(isDeliveredToTenant);
        setIsDeliveredToLandlord(isDeliveredToLandlord);
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

    public String getDateSent() {
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

    public void setIsDeliveredToTenant(boolean isDeliveredToTenant) {
        this.isDeliveredToTenant = isDeliveredToTenant;
    }

    public void setIsDeliveredToLandlord(boolean isDeliveredToLandlord) {
        this.isDeliveredToLandlord = isDeliveredToLandlord;
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

    private void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    private void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    private void setImageMessage(String imageMessage) {
        this.imageMessage = imageMessage;
    }

}
