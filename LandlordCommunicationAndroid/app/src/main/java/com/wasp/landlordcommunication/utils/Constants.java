package com.wasp.landlordcommunication.utils;

public class Constants {

    public static final String BASE_SERVER_URL = "http://192.168.1.77:8085/api/";
    public static final String SLASH_STRING_VALUE = "/";
    public static final String USERS_URL_SUFFIX = "users";
    public static final String USERS_LOGIN_URL_SUFFIX = "/login";
    public static final String USERS_GET_BY_USERNAME_URL_SUFFIX = "/";
    public static final String BASE_SERVER_URL_VALUE_NAME = "baseServerUrl";
    public static final String JSON_MEDIA_TYPE = "application/json";

    public static final String LOGIN_EMPTY_FIELDS_MESSAGE = "Please fill in all fields!";
    public static final String LOGIN_INVALID_FIELDS_MESSAGE = "All fields must contain at least 3 symbols!";
    public static final String LOGIN_INVALID_USERNAME_OR_PASSWORD_MESSAGE = "Invalid username or password!";
    public static final String ERROR_MESSAGE = "Error: ";
    public static final int EMPTY_STRING_LENGTH_VALUE = 0;
    public static final int MIN_LENGTH_VALUE = 3;
    public static final int VISIBLE_CODE_VALUE = 0;

    public static final String EMAIL_AND_PUBLIC_PROFILE = "public_profile";
    public static final String UNSUCCESSFUL_LOGIN = "Unsuccessful login!";
    public static final String SUCCESSFUL_LOGIN = "Login successful!";
    public static final String LOGIN_CANCELED_MESSAGE = "Login canceled";
    public static final String SELECT_USER_TYPE_TITLE = "Choose user type";
    public static final String CONFIRMATION = "OK";
    public static final String DISAGREEMENT = "Cancel";
    public static final String USER_EXTRA = "user";
    public static final int INITIAL_SELECTION = 0;
    public static final String MY_PAYMENTS_DRAWER_ITEM_NAME = "My payments";
    public static final String LANDLORDS_LIST_DRAWER_ITEM_NAME = "All landlords";
    public static final String CHAT_DRAWER_ITEM_NAME = "Chat";
    public static final String SETTINGS_DRAWER_ITEM_NAME = "Settings";
    public static final String HOME_DRAWER_ITEM_NAME = "Home";
    public static final String TENANT = "Tenant";
    public static final String LANDLORD = "Landlord";
    public static final String MY_PLACES_DRAWER_ITEM_NAME = "My places";
    public static final String MY_PROPERTIES_DRAWER_ITEM_NAME = "My properties";
    public static final String RATINGS_URL_SUFFIX = "ratings";
    public static final String RATINGS_CHECK_URL_SUFFIX = "/check";
    public static final String RATINGS_GET_BY_USERNAME_URL_SUFFIX = "/";


    public static final String PREFERENCES_USER_ID_KEY = "userId";
    public static final String PREFERENCES_USER_NAME_KEY = "userName";
    public static final String PREFERENCES_USER_FULL_NAME_KEY = "fullName";
    public static final String PREFERENCES_USER_TYPE_KEY = "userType";

    public static final String PASSWORDS_MATCH_ERROR_MESSAGE = "Passwords do not match!";
    public static final String USERNAME_ALREADY_TAKEN_MESSAGE = "Username is already taken!";
    public static final int MIN_NAME_LENGTH = 2;
    public static final String NAME_FIELDS_ERROR_MESSAGE = "First and last name must contain at least 2 symbols!";
    public static final String UNSUCCESSFUL_REGISTRATION = "Registration failed!";
    public static final String NO_PAYMENTS_AVAILABLE_MESSAGE = "Currently you don't have any payments";
    public static final String IMAGE_FILE_TYPE = "image/*";
    public static final String DATA_EXTRA = "data";
    public static final String IMAGE_CHANGE_ERROR_MESSAGE = "Profile image update failed!";
    public static final int IMAGE_QUALITY = 25;
    public static final String ERROR_LOADING_USER_IMAGE = "Unsuccessful profile image loading!";
    public static final String PAYMENTS_URL_SUFFIX = "payments";
    public static final String PAYMENTS_GET_BY_ID_URL_SUFFIX = "/";
    public static final String EMPTY_STRING = "";
    public static final String PAYMENTS_SENT_TITLE = "Sent payments";
    public static final String PAYMENTS_RECEIVED_TITLE = "Received payments";
    public static final String TIME_ZONE_NAME = "timeZone";
    public static final String DATE_REPRESENTATION_NAME = "dateRepresentation";
    public static final String TIME_ZONE = "UTC";
    public static final String PAYMENTS_DATE_REPRESENTATION = "dd.MM.yyyy  HH:mm:ss";
    public static final String NO_LANDLORDS_AVAILABLE_MESSAGE = "There are currently no landlords at the moment";
    public static final String NO_RESULT_ON_SEARCH_QUERY_MESSAGE = "Your search returned zero results";

}