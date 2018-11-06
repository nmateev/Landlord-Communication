package com.wasp.landlordcommunication.models;

public class Payment {

    private int paymentId;
    private int tenantId;
    private int landlordId;
    private String propertyAddress;
    private int propertyId;
    private double paymentAmount;
    private String datePaid;
    private String cardNumber;

    public Payment() {

    }

    public Payment(int tenantId, int landlordId, String propertyAddress, int propertyId, double paymentAmount, String datePaid, String cardNumber) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setPropertyAddress(propertyAddress);
        setPropertyId(propertyId);
        setPaymentAmount(paymentAmount);
        setDatePaid(datePaid);
        setCardNumber(cardNumber);
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

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getDatePaid() {
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

    private void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    private void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    private void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    private void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}