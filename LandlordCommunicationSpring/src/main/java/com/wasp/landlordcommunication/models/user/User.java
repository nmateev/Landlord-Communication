package com.wasp.landlordcommunication.models.user;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.USERS_TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USERS_TABLE_ID_COLUMN_NAME)
    private int userId;

    @Column(name = Constants.USERS_TABLE_USER_NAME_COLUMN)
    private String userName;

    @Column(name = Constants.USERS_TABLE_USER_PASSWORD_COLUMN)
    private String userPassword;

    @Column(name = Constants.USERS_TABLE_USER_PASSWORD_SALT_COLUMN)
    private String userPasswordSalt;

    @Column(name = Constants.USERS_TABLE_USER_FIRST_NAME_COLUMN)
    private String firstName;

    @Column(name = Constants.USERS_TABLE_USER_LAST_NAME_COLUMN)
    private String lastName;

    @Column(name = Constants.USERS_TABLE_USER_TYPE_COLUMN)
    private String userType;

    @Column(name = Constants.USERS_TABLE_USER_PICTURE_COLUMN)
    private byte[] userPicture;

    @Column(name = Constants.USERS_TABLE_USER_VOTE_COUNT_COLUMN)
    private long userVoteCount;

    @Column(name = Constants.USERS_TABLE_USER_VOTE_SUM_COLUMN)
    private double userVoteSum;

    @Column(name = Constants.USERS_TABLE_USER_RATING_COLUMN)
    private double userRating;


    public User() {

    }

    public User(String userName, String userPassword, String userPasswordSalt, String firstName, String lastName, String userType, byte[] userPicture,
                long userVoteCount, double userVoteSum, double userRating) {
        setUserName(userName);
        setUserPassword(userPassword);
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
