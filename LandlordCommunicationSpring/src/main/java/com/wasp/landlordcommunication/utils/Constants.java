package com.wasp.landlordcommunication.utils;

public class Constants {


    public static final String HIBERNATE_CONFIGURATION_FILE_NAME = "hibernate.cfg.xml";
    public static final String TIME_ZONE_CONFIGURATION_VALUE = "UTC";

    public static final String TEMPLATE_MESSAGES_ROOT_MAPPING = "/api/templates";
    public static final String TEMPLATE_MESSAGES_TABLE_NAME = "template_messages";
    public static final String TEMPLATE_MESSAGES_ID_COLUMN_NAME = "template_message_id";
    public static final String TEMPLATE_MESSAGES_TEXT_COLUMN_NAME = "template_text";
    public static final String TEMPLATE_MESSAGES_TYPE_COLUMN_NAME = "template_type";

    public static final String PROPERTIES_ROOT_MAPPING = "/api/properties";
    public static final String PROPERTIES_TABLE_NAME = "properties";
    public static final String PROPERTIES_ID_COLUMN_NAME = "property_id";
    public static final String PROPERTIES_TENANT_COLUMN_NAME = "tenant_id";
    public static final String PROPERTIES_LANDLORD_COLUMN_NAME = "landlord_id";
    public static final String PROPERTIES_RENT_PRICE_COLUMN_NAME = "rent_price";
    public static final String PROPERTIES_DUE_DATE_COLUMN_NAME = "due_date";
    public static final String PROPERTIES_IS_RENT_PAID_COLUMN_NAME = "is_rent_paid";
    public static final String PROPERTIES_PICTURE_COLUMN_NAME = "property_picture";
    public static final String PROPERTIES_ADDRESS_COLUMN_NAME = "property_address";
    public static final String PROPERTIES_DESCRIPTION_COLUMN_NAME = "description";

    public static final String USER_ROOT_MAPPING = "api/users";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_TABLE_ID_COLUMN_NAME = "user_id";
    public static final String USERS_TABLE_USER_NAME_COLUMN = "user_name";
    public static final String USERS_TABLE_USER_PASSWORD_COLUMN = "user_password";
    public static final String USERS_TABLE_USER_PASSWORD_SALT_COLUMN = "user_password_salt";
    public static final String USERS_TABLE_USER_TYPE_COLUMN = "user_type";
    public static final String USERS_TABLE_USER_FIRST_NAME_COLUMN = "first_name";
    public static final String USERS_TABLE_USER_LAST_NAME_COLUMN = "last_name";
    public static final String USERS_TABLE_USER_PICTURE_COLUMN = "user_picture";
    public static final String USERS_TABLE_USER_VOTE_COUNT_COLUMN = "user_vote_count";
    public static final String USERS_TABLE_USER_VOTE_SUM_COLUMN = "user_vote_sum";
    public static final String USERS_TABLE_USER_RATING_COLUMN = "user_rating";

    public static final String CHAT_SESSIONS_ROOT_MAPPING = "api/chatsessions";
    public static final String CHAT_SESSIONS_TABLE_NAME = "chat_sessions";
    public static final String CHAT_SESSIONS_TABLE_ID_FIELD = "chat_session_id";
    public static final String CHAT_SESSIONS_TABLE_DATE_CREATED_FIELD = "date_created";
    public static final String CHAT_SESSIONS_TABLE_TENANT_ID_FIELD = "tenant_id";
    public static final String CHAT_SESSIONS_TABLE_LANDLORD_ID_FIELD = "landlord_id";

    public static final String PAYMENTS_ROOT_MAPPING = "api/payments";
    public static final String PAYMENTS_TABLE = "payments";
    public static final String PAYMENTS_ID_COLUMN = "payment_id";
    public static final String PAYMENTS_TENANT_ID_COLUMN = "tenant_id";
    public static final String PAYMENTS_LANDLORD_ID_COLUMN = "landlord_id";
    public static final String PAYMENTS_PROPERTY_ID_COLUMN = "property_id";
    public static final String PAYMENTS_AMOUNT_COLUMN = "amount";
    public static final String PAYMENTS_DATE_PAID_COLUMN = "date_paid";
    public static final String PAYMENTS_CARD_NUMBER_COLUMN = "card_number";

    public static final String CHAT_MESSAGES_ROOT_MAPPING ="/api/messages" ;
    public static final String CHAT_MESSAGES_TABLE_NAME = "chat_messages";
    public static final String CHAT_MESSAGES_ID_COLUMN = "message_id";
    public static final String CHAT_MESSAGES_TENANT_COLUMN = "tenant_id";
    public static final String CHAT_MESSAGES_LANDLORD_COLUMN = "landlord_id";
    public static final String CHAT_MESSAGES_CHAT_SESSION_ID_COLUMN = "chat_session_id";
    public static final String CHAT_MESSAGES_DATE_SENT_COLUMN = "date_sent";
    public static final String CHAT_MESSAGES_TEXT_COLUMN = "message_text";
    public static final String CHAT_MESSAGES_IMAGE_COLUMN = "image_message";
    public static final String CHAT_MESSAGES_IS_DELIVERED_TO_TENANT_COLUMN = "is_delivered_to_tenant";
    public static final String CHAT_MESSAGES_IS_DELIVERED_TO_LANDLORD_COLUMN = "is_delivered_to_landlord";



}

