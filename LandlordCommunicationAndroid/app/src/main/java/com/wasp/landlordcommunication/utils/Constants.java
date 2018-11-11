package com.wasp.landlordcommunication.utils;

public class Constants {

    public static final String BASE_SERVER_URL = "http://192.168.1.77:9999/api/";
    // public static final String BASE_SERVER_URL = "http://192.168.0.103:9999/api/";
    // public static final String BASE_SERVER_URL = "http://10.178.154.95:9999/api/";
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
    public static final int IMAGE_QUALITY = 50;
    public static final String ERROR_LOADING_USER_IMAGE = "Unsuccessful profile image loading!";
    public static final String PAYMENTS_URL_SUFFIX = "payments";
    public static final String EMPTY_STRING = "";
    public static final String PAYMENTS_SENT_TITLE = "Sent payments";
    public static final String PAYMENTS_RECEIVED_TITLE = "Received payments";
    public static final String TIME_ZONE_NAME = "timeZone";
    public static final String DATE_REPRESENTATION_NAME = "dateRepresentation";
    public static final String TIME_ZONE = "UTC";
    public static final String DATE_REPRESENTATION = "dd.MM.yyyy  HH:mm:ss";
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
    public static final String INDIVIDUALISATION = "Your ";
    public static final String SUBMIT_OPTION = "Submit";
    public static final String CANCEL_OPTION = "Cancel";

    public static final String ONE_STAR_RATING_TEXT = "Very Bad";
    public static final String TWO_STAR_RATING_TEXT = "Not Good";
    public static final String THREE_STAR_RATING_TEXT = "Neutral";
    public static final String FOUR_STAR_RATING_TEXT = "Very Good";
    public static final String FIVE_STAR_RATING_TEXT = "Excellent!";


    public static final String RATE_DIALOG_TITLE_MESSAGE = "RATE YOUR EXPERIENCE";
    public static final String RATE_DIALOG_DESCRIPTION_MESSAGE = "Please select some stars and give your feedback!";
    public static final String RATING_CANCELLED_MESSAGE = "Rating was cancelled!";
    public static final String ALREADY_RATED_MESSAGE = "You have already submitted your rating!";
    public static final String SUCCESSFUL_RATING = "Your feedback was successfully submitted!";
    public static final String UNEXPECTED_ERROR = "Something went wrong. Please try again!";
    public static final String LOGGED_IN_USER_ID_EXTRA = "loggedUser";
    public static final String CONTACTED_USER_ID_EXTRA = "contactedUser";
    public static final String MONTH_VALIDATION_ERROR = "Month value must be between 1 and 12";
    public static final String CARD_EXPIRATION_YEAR_ERROR_MESSAGE = "You card is expired";
    public static final String CARD_NUMBER_ERROR_MESSAGE = "Card number must be a 16 digit sequence found on the front of your credit card!";
    public static final String CARD_CVV_NUMBER_ERROR_MESSAGE = "Cvv number must be a 3 digit code found on the back of your credit card!";
    public static final String ALL_FIELDS_MUST_BE_FILLED_IN = "You must fill in al fields!";
    public static final String SUCCESSFUL_PAYMENT_MESSAGE = "You have successfully paid your rent for this month!";
    public static final String ALREADY_PAID_RENT_MESSAGE = "You have already paid your rent for this month!";
    public static final String INPUT_NOT_CORRECT_MESSAGE = "You must enter a valid number!";
    public static final String RENT_AMOUNT_BOUNDS_NOT_MET_MESSAGE = "Rent price must be between 1 and 3500!";
    public static final double RENT_AMOUNT_MIN_BOUND = 1;
    public static final double RENT_AMOUNT_MAX_BOUND = 3500;
    public static final String SUCCESSFUL_CHANGE_OF_RENT_MESSAGE = "You have successfully changed the rent price!";
    public static final String USER_PROFILE_IMAGE_KEY = "userImage";
    public static final String PREFERENCE_SAVED_MESSAGE = "Preference saved successfully! ";
    public static final String PREFERENCE_PROPERTIES_LAYOUT_VIEW_SELECTION_MESSAGE = " view is selected.";

    public static final String PREFERENCE_PROPERTIES_LAYOUT_DESCRIPTION_FOR_TENANT = "Choose layout for how your places wil be visualised";
    public static final String PREFERENCE_PROPERTIES_LAYOUT_DESCRIPTION_FOR_LANDLORD = "Choose layout for how your properties wil be visualised";

    public static final String RATING_REPRESENTATION = "/5";
    public static final String NOTIFICATION_CHANNEL_NAME = "Rent";
    public static final String RENT_NOTIFICATION_CHANNEL_DESCRIPTION = "Channel to receive notifications about upcoming rents that are due";
    public static final String RENT_CHANNEL_ID = "Rent";
    public static final String PURPOSE_EXTRA = "purpose";
    public static final String NOTIFICATION_CODE_EXTRA = "notificationCode";
    public static final String NOTIFICATION_TITLE_EXTRA = "notificationTitle";
    public static final String NOTIFICATION_DESCRIPTION_EXTRA = "notificationDescription";
    public static final String NOTIFICATION_DESCRIPTION_ADDRESS_EXTRA = "address";
    public static final String RENT_NOTIFICATION_TITLE = "Upcoming rent";
    public static final String RENT_NOTIFICATION_DESCRIPTION = "Rent is due in 5 days for your place at";
    public static final String RENT_NOTIFICATION_COLOUR_STRING = "#641D79";
    public static final int RENT_NOTIFICATION_DAYS_BEFORE_PERIOD = -5;
    public static final int RENT_NOTIFICATION_HOUR_TRIGGER = 11;
    public static final int RENT_NOTIFICATION_MINUTE_TRIGGER = 26;
    public static final String CHAT_SESSIONS_URL_SUFFIX = "chatsessions";
    public static final String USERS_TYPE_SUFFIX = "type";

    public static final String CHAT_SESSIONS_RELATION_SUFFIX = "relation";
    public static final String CHAT_SESSIONS_CHECK_SUFFIX = "check";
    public static final String NO_CHAT_SESSIONS_MESSAGE = "Your chat's list is empty.";
    public static final String CHAT_SESSION_ID_EXTRA = "chatSessionId";
    public static final String CHAT_MESSAGES_URL_SUFFIX = "messages";
    public static final String CHAT_SESSION_TENANT_ID_EXTRA = "tenantExtra";
    public static final String CHAT_SESSION_LANDLORD_ID_EXTRA = "landlordExtra";
    public static final String CHAT_TIME_FORMATTER = "chatFormatter";
    public static final String CHAT_DATE_REPRESENTATION = "HH:mm";
    public static final String PREFERENCES_TEMPLATE_MESSAGES_FORMALITY_KEY = "templateFormality";
    public static final String NORMAL = "Normal";
    public static final String FORMAL = "Formal";
    public static final String TEMPLATE_MESSAGES_URL_SUFFIX = "templates";
    public static final String PREFERENCE_FORMALITY_LEVEL_SELECTION_MESSAGE = " template type is selected.";
    public static final String PLACE_NOT_RENTED_NO_OPTION_TO_RATE_MESSAGE = "This place is not rented. There is no tenant to rate!";
    public static final String PLACE_NOT_RENTED_NO_OPTION_TO_CHAT_MESSAGE = "This place is not rented. There is no tenant to message!";
    public static final int SHAKE_ANIMATION_DURATION_VALUE = 1500;
}