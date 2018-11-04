package com.wasp.landlordcommunication.models;

import java.util.Date;

public class ChatSession {

    private int chatSessionId;
    private Date dateCreated;
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

    public ChatSession(Date dateCreated, int tenantId, int landlordId) {
        this(tenantId, landlordId);
        setDateCreated(dateCreated);
    }

    public int getChatSessionId() {
        return chatSessionId;
    }

    public Date getDateCreated() {
        return dateCreated;
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

    private void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    private void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

}
