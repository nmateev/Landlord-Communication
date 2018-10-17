package com.wasp.landlordcommunication.models;


import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = Constants.PROPERTY_TABLE_NAME)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PROPERTY_ID_COLUMN_NAME)
    private int propertyId;

    @Column(name = Constants.PROPERTIES_RENT_PRICE_COLUMN_NAME)
    private double rentPrice;

    @Column(name = Constants.PROPERTIES_DUE_DATE_COLUMN_NAME)
    private Date dueDate;

    @Column(name = Constants.PROPERTIES_IS_RENT_PAID_COLUMN_NAME)
    private boolean isRentPaid;

    @Column(name = Constants.PROPERTIES_ADDRESS_COLUMN_NAME)
    private String propertyAddress;

    @Column(name = Constants.PROPERTIES_PICTURE_COLUMN_NAME)
    private byte[] propertyPicture;

    @Column(name = Constants.PROPERTIES_DESCRIPTION_COLUMN_NAME)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = Constants.USERS_PROPERTIES_TABLE,
            joinColumns = @JoinColumn(name = Constants.PROPERTY_TABLE_ID_FIELD),
            inverseJoinColumns = @JoinColumn(name = Constants.USERS_TABLE_ID_COLUMN_NAME)
    )
    private Set<User> users;

    @OneToMany(mappedBy = "property")
    private Set<Payment> payments;

    public Property() {

    }


    public Property(double rentPrice, Date dueDate, boolean isRentPaid, String propertyAddress, byte[] propertyPicture, String description) {
        setRentPrice(rentPrice);
        setDueDate(dueDate);
        setPropertyAddress(propertyAddress);
        setPropertyPicture(propertyPicture);
        setDescription(description);
    }


    public int getPropertyId() {
        return propertyId;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean getIsRentPaid() {
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

    public Set<User> getUsers() {
        return users;
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
