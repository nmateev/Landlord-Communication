package com.wasp.landlordcommunication.models;

import java.util.Date;

public class Payment {

    private int paymentId;
    private int tenantId;
    private int landlordId;
    private int propertyId;
    private double amount;
    private Date date_paid;
    private String card_number;

    public Payment(){

    }


    public Payment(int paymentId, int tenantId, int landlordId, int propertyId, double amount, Date date_paid, String card_number) {
        this.paymentId = paymentId;
        this.tenantId = tenantId;
        this.landlordId = landlordId;
        this.propertyId = propertyId;
        this.amount = amount;
        this.date_paid = date_paid;
        this.card_number = card_number;
    }



    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate_paid() {
        return date_paid;
    }

    public void setDate_paid(Date date_paid) {
        this.date_paid = date_paid;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }


}