package com.wasp.landlordcommunication.models.user;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = Constants.USERS_TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USERS_TABLE_ID_COLUMN_NAME)
    private int userId;

    @NotNull
    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.USERS_TABLE_USER_NAME_COLUMN)
    private String userName;

    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.STRING_VALIDATION_MAX_TEXT)
    @Column(name = Constants.USERS_TABLE_USER_PASSWORD_COLUMN)
    private String userPassword;

    @NotNull
    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.USERS_TABLE_USER_FIRST_NAME_COLUMN)
    private String firstName;

    @NotNull
    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.USERS_TABLE_USER_LAST_NAME_COLUMN)
    private String lastName;

    @NotNull
    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.USERS_TABLE_USER_TYPE_COLUMN)
    private String userType;

    @Column(name = Constants.USERS_TABLE_USER_PICTURE_COLUMN)
    private byte[] userPicture;


    public User() {

    }

    public User(String userName, String userPassword, String firstName, String lastName, String userType, byte[] userPicture) {
        setUserName(userName);
        setUserPassword(userPassword);
        setFirstName(firstName);
        setLastName(lastName);
        setUserType(userType);
        setUserPicture(userPicture);
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserType() {
        return userType;
    }

    public byte[] getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(byte[] userPicture) {
        this.userPicture = userPicture;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setUserType(String userType) {
        this.userType = userType;
    }

}
