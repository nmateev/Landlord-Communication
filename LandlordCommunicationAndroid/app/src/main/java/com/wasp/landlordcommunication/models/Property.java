package com.wasp.landlordcommunication.models;

public class Property {

    private int propertyId;
    private int tenantId;
    private int landlordId;
    private double rentPrice;
    private int dueDate;
    private boolean rentPaid;
    private String propertyAddress;
    private String propertyPicture;
    private String description;

    public Property() {

    }

    public Property(int tenantId, int landlordId, double rentPrice, int dueDate, boolean rentPaid, String propertyAddress, String propertyPicture, String description) {
        setTenantId(tenantId);
        setLandlordId(landlordId);
        setRentPrice(rentPrice);
        setDueDate(dueDate);
        setRentPaid(rentPaid);
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

    public int getDueDate() {
        return dueDate;
    }

    public boolean getRentPaid() {
        return rentPaid;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public String getPropertyPicture() {
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
        this.rentPaid = rentPaid;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public void setPropertyPicture(String propertyPicture) {
        this.propertyPicture = propertyPicture;
    }

    private void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    private void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    private void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
}
