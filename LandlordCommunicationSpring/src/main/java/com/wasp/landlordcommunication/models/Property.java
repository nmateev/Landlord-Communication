package com.wasp.landlordcommunication.models;


import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = Constants.PROPERTIES_TABLE_NAME)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PROPERTIES_ID_COLUMN_NAME)
    private int propertyId;

    @Column(name = Constants.PROPERTIES_TENANT_COLUMN_NAME)
    private int tenantId;

    @NotNull
    @Column(name = Constants.PROPERTIES_LANDLORD_COLUMN_NAME)
    private int landlordId;

    @NotNull
    @Column(name = Constants.PROPERTIES_RENT_PRICE_COLUMN_NAME)
    private double rentPrice;

    @NotNull
    @Column(name = Constants.PROPERTIES_DUE_DATE_COLUMN_NAME)
    private Date dueDate;

    @NotNull
    @Column(name = Constants.PROPERTIES_IS_RENT_PAID_COLUMN_NAME)
    private boolean isRentPaid;

    @NotNull
    @Size(min = Constants.STRING_VALIDATION_MIN_TEXT, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.PROPERTIES_ADDRESS_COLUMN_NAME)
    private String propertyAddress;

    @Column(name = Constants.PROPERTIES_PICTURE_COLUMN_NAME)
    private byte[] propertyPicture;

    @NotNull
    @Size(min = Constants.STRING_VALIDATION_MIN_TEXT, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.PROPERTIES_DESCRIPTION_COLUMN_NAME)
    private String description;


    public Property() {

    }

    public Property(int tenantId, int landlordId, double rentPrice, Date dueDate, boolean isRentPaid, String propertyAddress, byte[] propertyPicture, String description) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setRentPrice(rentPrice);
        setDueDate(dueDate);
        setRentPaid(isRentPaid);
        setPropertyAddress(propertyAddress);
        setPropertyPicture(propertyPicture);
        setDescription(description);
    }

    public int getPropertyId() {
        return propertyId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean getRentPaid() {
        return isRentPaid;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public byte[] getPropertyPicture() {
        return propertyPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRentPaid(boolean rentPaid) {
        isRentPaid = rentPaid;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    private void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    private void setPropertyPicture(byte[] propertyPicture) {
        this.propertyPicture = propertyPicture;
    }

    private void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    private void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

}
