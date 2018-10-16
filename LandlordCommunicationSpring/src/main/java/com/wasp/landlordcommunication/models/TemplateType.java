package com.wasp.landlordcommunication.models;

import javax.persistence.*;

@Entity
@Table(name="template_types")
public class TemplateType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_type_id")
    private int templateTypeId;

    @Column(name="type")
    private String type;

    public TemplateType(){

    }

    public TemplateType(int templateTypeId,String type){
        this.templateTypeId=templateTypeId;
        this.type=type;
    }


    public int getTemplateTypeId() {
        return templateTypeId;
    }

    public void setTemplateTypeId(int templateTypeId) {
        this.templateTypeId = templateTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
