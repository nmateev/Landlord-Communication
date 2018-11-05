package com.wasp.landlordcommunication.models;

import java.util.Date;

public class ChatSession {

    private int chatSessionId;
    private int tenantId;
    private int landlordId;
    private User tenant;
    private User landlord;

    public ChatSession() {

    }

    public ChatSession(int tenantId, int landlordId) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
    }

    public int getChatSessionId() {
        return chatSessionId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public User getTenant() {
        return tenant;
    }

    public User getLandlord() {
        return landlord;
    }

    private void setChatSessionId(int chatSessionId) {
        this.chatSessionId = chatSessionId;
    }

    private void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

}
