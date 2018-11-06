package com.wasp.landlordcommunication.models;

import java.util.Date;

public class ChatMessage {


    private int messageId;
    private int tenantId;
    private int landlordId;
    private int senderId;
    private int chatSessionId;
    private Date dateSent;
    private String messageText;
    private String imageMessage;
    private boolean deliveredToTenant;
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
