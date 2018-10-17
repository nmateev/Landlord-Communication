package com.wasp.landlordcommunication.models;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.CHAT_MESSAGES_TABLE_NAME)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CHAT_MESSAGES_ID_COLUMN)
    private int messageId;

    @Column(name = Constants.CHAT_MESSAGES_SENDER_COLUMN)
    private int messageSender;

    @Column(name = Constants.CHAT_MESSAGES_RECIPIENT_COLUMN)
    private int messageRecipient;

    @Column(name = Constants.CHAT_MESSAGES_CHAT_SESSION_ID_COLUMN)
    private int chatSessionId;

    @Column(name = Constants.CHAT_MESSAGES_DATE_SENT_COLUMN)
    private Date dateSent;

    @Column(name = Constants.CHAT_MESSAGES_TEXT_COLUMN)
    private String messageText;

    @Column(name = Constants.CHAT_MESSAGES_IMAGE_COLUMN)
    private byte[] imageMessage;

    @ManyToOne
    @JoinColumn(name = Constants.CHAT_SESSIONS_TABLE_ID_FIELD, insertable = false, updatable = false)
    private ChatSession chatSession;


    public ChatMessage() {

    }

    public ChatMessage(int messageSender, int messageRecipient, int chatSessionId, Date dateSent, String messageText, byte[] imageMessage) {
        setMessageSender(messageSender);
        setMessageRecipient(messageRecipient);
        setChatSessionId(chatSessionId);
        setDateSent(dateSent);
        setMessageText(messageText);
        setImageMessage(imageMessage);
    }

    public int getMessageId() {
        return messageId;
    }

    public int getMessageSender() {
        return messageSender;
    }

    public int getMessageRecipient() {
        return messageRecipient;
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

    public byte[] getImageMessage() {
        return imageMessage;
    }

    public ChatSession getChatSession() {
        return chatSession;
    }

    private void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    private void setMessageSender(int messageSender) {
        this.messageSender = messageSender;
    }

    private void setMessageRecipient(int messageRecipient) {
        this.messageRecipient = messageRecipient;
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


    private void setImageMessage(byte[] imageMessage) {
        this.imageMessage = imageMessage;
    }
}
