package com.wasp.landlordcommunication.models;

public class User {

    private int userId;
    private String userName;
    private String userPassword;
    private String userPasswordSalt;
    private String firstName;
    private String lastName;
    private String userType;
    private byte[] userPicture;
    private long userVoteCount;
    private double userVoteSum;
    private double userRating;


    public User() {

    }

    public User(String userName, String userPassword) {
        setUserName(userName);
        setUserPassword(userPassword);
    }

    public User(String userName, String userPassword, String userPasswordSalt, String firstName, String lastName, String userType, byte[] userPicture,
                long userVoteCount, double userVoteSum, double userRating) {
        this(userName, userPassword);
        setUserPasswordSalt(userPasswordSalt);
        setFirstName(firstName);
        setLastName(lastName);
        setUserType(userType);
        setUserPicture(userPicture);
        setUserVoteCount(userVoteCount);
        setUserVoteSum(userVoteSum);
        setUserRating(userRating);
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

    public String getUserPasswordSalt() {
        return userPasswordSalt;
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

    public long getUserVoteCount() {
        return userVoteCount;
    }

    public double getUserVoteSum() {
        return userVoteSum;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserPicture(byte[] userPicture) {
        this.userPicture = userPicture;
    }

    public void setUserVoteCount(long userVoteCount) {
        this.userVoteCount = userVoteCount;
    }

    public void setUserVoteSum(double userVoteSum) {
        this.userVoteSum = userVoteSum;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
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

    private void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
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
