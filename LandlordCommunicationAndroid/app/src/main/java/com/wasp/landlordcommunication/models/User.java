package com.wasp.landlordcommunication.models;

public class User {

    private int userId;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String userType;
    private byte[] userPicture;


    public User() {

    }

    public User(String userName, String userPassword) {
        setUserName(userName);
        setUserPassword(userPassword);
    }

    public User(String userName, String userPassword, String firstName, String lastName, String userType, byte[] userPicture) {
        this(userName, userPassword);
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

    private void setUserType(String userType) {
        this.userType = userType;
    }

}
