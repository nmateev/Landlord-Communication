package com.wasp.landlordcommunication.models;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.PAYMENTS_TABLE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PAYMENTS_ID_COLUMN)
    private int paymentId;

    @Column(name = Constants.PAYMENTS_TENANT_ID_COLUMN)
    private int tenantId;

    @Column(name = Constants.PAYMENTS_LANDLORD_ID_COLUMN)
    private int landlordId;

    @Column(name = Constants.PAYMENTS_PROPERTY_ID_COLUMN)
    private int propertyId;

    @Column(name = Constants.PAYMENTS_AMOUNT_COLUMN)
    private double paymentAmount;

    @Column(name = Constants.PAYMENTS_DATE_PAID_COLUMN)
    private Date datePaid;

    @Column(name = Constants.PAYMENTS_CARD_NUMBER_COLUMN)
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = Constants.PROPERTIES_ID_COLUMN_NAME, insertable = false, updatable = false)
    private Property property;


    public Payment() {


    }

    public Payment(int tenantId, int landlordId, int propertyId, double paymentAmount, Date datePaid, String cardNumber) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
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

    public int getPropertyId() {
        return propertyId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public Property getProperty() {
        return property;
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

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
