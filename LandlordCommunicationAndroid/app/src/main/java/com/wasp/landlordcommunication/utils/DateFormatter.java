package com.wasp.landlordcommunication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

public class DateFormatter implements com.wasp.landlordcommunication.utils.base.DateFormatter {
    private SimpleDateFormat mDateFormatter;

    @Inject
    public DateFormatter(String dateRepresentation, String timeZone) {
        mDateFormatter = new SimpleDateFormat(dateRepresentation, Locale.UK);
        mDateFormatter.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    @Override
    public String formatDateToString(Date date) {
        return mDateFormatter.format(date);
    }
}
