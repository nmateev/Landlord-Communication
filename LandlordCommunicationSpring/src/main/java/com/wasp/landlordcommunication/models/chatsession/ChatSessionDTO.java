package com.wasp.landlordcommunication.models.chatsession;

public class ChatSessionDTO {

    private int tenantId;
    private int landlordId;

    public ChatSessionDTO() {

    }

    public ChatSessionDTO(int tenantId, int landlordId) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
    }

    public int getTenantId() {
        return tenantId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    private void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }
}
