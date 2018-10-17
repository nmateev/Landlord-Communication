package com.wasp.landlordcommunication.models;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = Constants.CHAT_SESSIONS_TABLE_NAME)
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_SESSIONS_TABLE_ID_FIELD)
    private int chatSessionId;

    @Column(name = Constants.CHAT_SESSIONS_TABLE_DATE_CREATED_FIELD)
    private Date dateCreated;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = Constants.USERS_CHAT_SESSIONS_TABLE,
            joinColumns = @JoinColumn(name = Constants.CHAT_SESSIONS_TABLE_ID_FIELD),
            inverseJoinColumns = @JoinColumn(name = Constants.USERS_TABLE_ID_COLUMN_NAME)
    )
    private Set<User> users;


    @OneToMany(mappedBy = "chatSession")
    private Set<ChatMessage> chatMessages;

    public ChatSession() {

    }

    public ChatSession(Date dateCreated, Set<User> users) {
        setDateCreated(dateCreated);
        setUsers(users);
    }

    public int getChatSessionId() {
        return chatSessionId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    private void setChatSessionId(int chatSessionId) {
        this.chatSessionId = chatSessionId;
    }

    private void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    private void setUsers(Set<User> users) {
        this.users = users;
    }
}
