package com.wasp.landlordcommunication.models.user;

public class UserDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String userType;
    private byte[] userPicture;
    private double userRating;

    public UserDTO() {

    }

    public UserDTO(int userId, String firstName, String lastName, String userType, byte[] userPicture, double userRating) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUserType(userType);
        setUserPicture(userPicture);
        setUserRating(userRating);
    }

    public int getUserId() {
        return userId;
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

    public double getUserRating() {
        return userRating;
    }

    private void setUserId(int userId) {
        this.userId = userId;
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

    private void setUserPicture(byte[] userPicture) {
        this.userPicture = userPicture;
    }

    private void setUserRating(double userRating) {
        this.userRating = userRating;
    }
}
