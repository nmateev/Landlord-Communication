package com.wasp.landlordcommunication.models;

import java.util.Date;

public class Payment {

    private int paymentId;
    private int tenantId;
    private int landlordId;
    private int propertyId;
    private double paymentAmount;
    private Date datePaid;
    private String cardNumber;
    private Property property;

    public Payment() {

    }

    public Payment(int paymentId, int tenantId, int landlordId, int propertyId, double paymentAmount, Date datePaid, String cardNumber, Property property) {
        setPaymentId(paymentId);
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setPropertyId(propertyId);
        setPaymentAmount(paymentAmount);
        setDatePaid(datePaid);
        setCardNumber(cardNumber);
        setProperty(property);
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    private void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    private void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    private void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    private void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    private void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public Property getProperty() {
        return property;
    }

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private void setProperty(Property property) {
        this.property = property;
    }

}