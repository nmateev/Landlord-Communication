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
    public static final String MY_PAYMENTS_DRAWER_ITEM_NAME = "My Payments";
    public static final String LANDLORDS_LIST_DRAWER_ITEM_NAME = "All Landlords";
    public static final String CHAT_DRAWER_ITEM_NAME = "Chat";
    public static final String SETTINGS_DRAWER_ITEM_NAME = "Settings";
    public static final String HOME_DRAWER_ITEM_NAME = "Home";
    public static final String TENANT = "Tenant";
    public static final String LANDLORD = "Landlord";
    public static final String MY_PLACES_DRAWER_ITEM_NAME = "My Places";
    public static final String MY_PROPERTIES_DRAWER_ITEM_NAME = "My Properties";
    public static final String RATINGS_URL_SUFFIX = "ratings";
    public static final String RATINGS_CHECK_URL_SUFFIX = "/check";
    public static final String RATINGS_GET_BY_USERNAME_URL_SUFFIX = "/";


    public static final String PREFERENCES_USER_ID_KEY = "userId";
    public static final String PREFERENCES_USER_NAME_KEY = "userName";
    public static final String PREFERENCES_USER_FULL_NAME_KEY = "fullName";
    public static final String PREFERENCES_USER_TYPE_KEY = "userType";
    public static final String PREFERENCES_PROPERTY_LISTING_TYPE_KEY = "listingType";

    public static final String PASSWORDS_MATCH_ERROR_MESSAGE = "Passwords do not match!";
    public static final String USERNAME_ALREADY_TAKEN_MESSAGE = "Username is already taken!";
    public static final int MIN_NAME_LENGTH = 2;
    public static final String NAME_FIELDS_ERROR_MESSAGE = "First and last name must contain at least 2 symbols!";
    public static final String UNSUCCESSFUL_REGISTRATION = "Registration failed!";
    public static final String NO_PAYMENTS_AVAILABLE_MESSAGE = "Currently you don't have any payments";
    public static final String IMAGE_FILE_TYPE = "image/*";
    public static final String DATA_EXTRA = "data";
    public static final String IMAGE_CHANGE_ERROR_MESSAGE = "Profile image update failed!";
    public static final int IMAGE_QUALITY = 35;
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
    public static final String PRICE = "Price: ";
    public static final String PROPERTY_ID_EXTRA = "propertyId";
    public static final String RENT_DUE_TITLE = "Rent is due every month on ";

    public static final String PROPERTIES_URL_SUFFIX = "properties";
    public static final String NO_PROPERTIES_MESSAGE = "This landlord currently have no properties for rent";
    public static final String PROPERTY_ALREADY_RENTED = "This property is already rented";
    public static final String RENT_CONFIRMATION_DIALOG_TITLE = "Rent confirmation!";
    public static final String RENT_CONFIRMATION_DIALOG_COLOUR_STRING = "#641D79";
    public static final String RENT_CONFIRMATION_DIALOG_COLOUR_NEGATIVE_BUTTON_STRING = "#FF0000";
    public static final String RENT_CONFIRMATION_DIALOG_COLOUR_GREY_BUTTON_STRING = "#D3D3D3";
    public static final String RENT_CONFIRMATION_DIALOG_QUESTION_MESSAGE = "Do you really want to rent this place?";
    public static final String RENT_CONFIRMATION_DIALOG_CANCEL_OPTION_TEXT = "Cancel";
    public static final String RENT_CONFIRMATION_DIALOG_CONFIRMATION_OPTION_TEXT = "Yes, rent it!";
    public static final String ALREADY_RENTING_PROPERTY_MESSAGE = "You already rent this place!";
    public static final String CONFIRMATION_NOT_GIVEN = "Confirmation was declined";
    public static final String SUCCESS = "SUCCESS";
    public static final String MANAGE_PLACES_MESSAGE = "You can manage your place at My Places section";
    public static final String OK_TEXT = "OK";
    public static final String NO_RENTED_PLACES_MESSAGE = "No results!\nCurrently you don't rent any place.";
    public static final String NO_PROPERTIES_FOR_RENT_MESSAGE = "No results!\nCurrently you don't offer any properties for rent.";
    public static final String RENT_IS_DUE_AT = "Rent is due on: ";
    public static final String COMPACT_VIEW_STYLE = "Compact";
    public static final String DETAILED_VIEW_STYLE = "Detailed";
    public static final String COLOUR_RED_STRING = "#FF0000";
    public static final String COLOUR_GREEN_STRING = "#51EB17";
    public static final String PAID = "Paid";
    public static final String NOT_PAID = "Not Paid";


    public static final String FILTER_OPTION_ALL = "All";
    public static final String FILTER_OPTION_PAID = "Only paid";
    public static final String FILTER_OPTION_NOT_PAID = "Only not paid";
    public static final String FILTER_OPTION_ASCENDING_PRICE = "Price ascending";
    public static final String FILTER_OPTION_DESCENDING_PRICE = "Price descending";
}