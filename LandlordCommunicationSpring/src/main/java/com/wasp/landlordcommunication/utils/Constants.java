package com.wasp.landlordcommunication.utils;

public class Constants {
    public static final String HIBERNATE_CONFIGURATION_FILE_NAME = "hibernate.cfg.xml";
    public static final String TEMPLATE_MESSAGES_ROOT_MAPPING = "/api/template";

    public static final String TEMPLATE_MESSAGES_TABLE_NAME = "template_messages";
    public static final String TEMPLATE_MESSAGES_ID_COLUMN_NAME = "template_message_id";
    public static final String TEMPLATE_MESSAGES_TEXT_COLUMN_NAME = "template_text";
    public static final String TEMPLATE_MESSAGES_TYPE_COLUMN_NAME = "template_type";


    public static final String PROPERTY_ID_COLUMN_NAME ="property_id" ;
    public static final String PROPERTY_TABLE_NAME ="properties";
    public static final String PROPERTIES_RENT_PRICE_COLUMN_NAME ="rent_price";
    public static final String PROPERTIES_DUE_DATE_COLUMN_NAME ="due_date";
    public static final String PROPERTIES_ADDRESS_COLUMN_NAME ="property_address";
    public static final String PROPERTIES_PICTURE_COLUMN_NAME ="property_picture" ;
    public static final String PROPERTIES_DESCRIPTION_COLUMN_NAME ="description" ;
    public static final String PROPERTY_ROOT_MAPPING ="/api/properties" ;

    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_TABLE_ID_COLUMN_NAME = "user_id";
    public static final String USERS_TABLE_USER_NAME_COLUMN = "user_name";

    public static final String USERS_TABLE_USER_PASSWORD_COLUMN = "user_password";
    public static final String USERS_TABLE_USER_PASSWORD_SALT_COLUMN = "user_password_salt";
    public static final String USERS_TABLE_USER_TYPE_COLUMN = "user_type";
    public static final String USERS_TABLE_USER_PICTURE_COLUMN = "user_picture";
    public static final String USERS_TABLE_USER_VOTE_COUNT_COLUMN = "user_vote_count";
    public static final String USERS_TABLE_USER_VOTE_SUM_COLUMN = "user_vote_sum";
    public static final String USERS_TABLE_USER_RATING_COLUMN = "user_rating";
    public static final String USERS_TABLE_USER_FIRST_NAME_COLUMN = "first_name";
    public static final String USERS_TABLE_USER_LAST_NAME_COLUMN = "last_name";
    public static final String CHAT_SESSIONS_TABLE_NAME = "chat_sessions";
    public static final String CHAT_SESSIONS_TABLE_ID_FIELD = "chat_session_id";
    public static final String CHAT_SESSIONS_TABLE_DATE_CREATED_FIELD = "chat_session_id";
    public static final String ISSUE_REPORTS_TABLE_NAME = "issue_reports";
    public static final String ISSUE_REPORTS_ID_COLUMN_NAME = "issue_id";
    public static final String ISSUE_REPORTS_ISSUE_STATUS_COLUMN_NAME = "issue_status";
    public static final String ISSUE_REPORTS_USER_ID_COLUMN_NAME = "user_id";
    public static final String ISSUE_REPORTS_PROPERTY_ID_COLUMN_NAME = "property_id";
    public static final String ISSUE_REPORTS_ISSUE_TEXT_COLUMN_NAME = "issue_report_text";
    public static final String ISSUE_REPORTS_ISSUE_DATE_COLUMN_NAME = "issue_date";

    public static final String USERS_CHAT_SESSIONS_TABLE = "users_chat_sessions";


    public static final String USERS_PROPERTIES_TABLE ="users_properties" ;
    public static final String PROPERTY_TABLE_ID_FIELD = "property_id";
}

