package com.wasp.landlordcommunication.models;

import javax.persistence.*;

@Entity
@Table(name = "template_messages")
public class TemplateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_message_id")
    private int id;

    @Column(name = "template_text")
    private String text;

    @OneToOne
    @JoinColumn(name="template_type")
    private TemplateType messageType;

    public TemplateMessage(){

    }

    public TemplateMessage(int id,String text,TemplateType messageType){
        this.id=id;
        this.text=text;
        this.messageType=messageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TemplateType getMessageType() {
        return messageType;
    }

    public void setMessageType(TemplateType messageType) {
        this.messageType = messageType;
    }
}
