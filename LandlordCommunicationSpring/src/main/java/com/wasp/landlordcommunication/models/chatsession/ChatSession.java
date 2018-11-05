package com.wasp.landlordcommunication.models.chatsession;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Constants.CHAT_SESSIONS_TABLE_NAME)
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_SESSIONS_TABLE_ID_FIELD)
    private int chatSessionId;

    @NotNull
    @Column(name = Constants.CHAT_SESSIONS_TABLE_TENANT_ID_FIELD)
    private int tenantId;

    @NotNull
    @Column(name = Constants.CHAT_SESSIONS_TABLE_LANDLORD_ID_FIELD)
    private int landlordId;

    @ManyToOne
    @JoinColumn(name = Constants.CHAT_SESSIONS_TABLE_TENANT_ID_FIELD, referencedColumnName = Constants.USERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private User tenant;

    @ManyToOne
    @JoinColumn(name = Constants.CHAT_SESSIONS_TABLE_LANDLORD_ID_FIELD, referencedColumnName = Constants.USERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
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
