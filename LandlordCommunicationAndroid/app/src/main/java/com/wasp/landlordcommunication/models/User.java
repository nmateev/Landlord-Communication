package com.wasp.landlordcommunication.models;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String userType;
    private String userPicture;


    public User() {

    }

    public User(String userName, String userPassword) {
        setUserName(userName);
        setUserPassword(userPassword);
    }

    public User(String userName, String firstName, String lastName) {
        setUserName(userName);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User(String userName, String userPassword, String firstName, String lastName, String userType) {
        this(userName, firstName,lastName);
        setUserPassword(userPassword);
        setUserType(userType);
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

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
