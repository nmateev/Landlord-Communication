package com.wasp.landlordcommunication.models;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.USERS_PAYMENTS_TABLE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USER_PAYMENTS_ID_COLUMN)
    private int paymentId;

    @Column(name = Constants.USER_PAYMENTS_USER_ID_COLUMN)
    private int userId;

    @Column(name = Constants.USER_PAYMENTS_PROPERTY_ID_COLUMN)
    private int propertyId;

    @Column(name = Constants.USER_PAYMENTS_AMOUNT_COLUMN)
    private double paymentAmount;

    @Column(name = Constants.USER_PAYMENTS_DATE_PAID_COLUMN)
    private Date datePaid;

    @Column(name = Constants.USER_PAYMENTS_CARD_NUMBER_COLUMN)
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = Constants.USERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = Constants.PROPERTY_TABLE_ID_FIELD, insertable = false, updatable = false)
    private Property property;


    public Payment() {


    }

    public Payment(int userId, int propertyId, double paymentAmount, Date datePaid, String cardNumber) {
        setUserId(userId);
        setPropertyId(propertyId);
        setPaymentAmount(paymentAmount);
        setDatePaid(datePaid);
        setCardNumber(cardNumber);
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public int getUserId() {
        return userId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public User getUser() {
        return user;
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

    private void setUserId(int userId) {
        this.userId = userId;
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
