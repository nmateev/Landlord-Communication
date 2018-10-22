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

    @Size(min = Constants.TEXT_VALIDATION_MIN_VALUE, max = Constants.TEXT_VALIDATION_MAX_VALUE)
    @Column(name = Constants.USERS_TABLE_USER_PASSWORD_SALT_COLUMN)
    private String userPasswordSalt;

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

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private void setUserName(String userName) {
        this.userName = userName;
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
