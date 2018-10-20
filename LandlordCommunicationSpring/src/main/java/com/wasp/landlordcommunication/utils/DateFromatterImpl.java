package com.wasp.landlordcommunication.utils;

import com.sun.org.apache.regexp.internal.RE;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFromatterImpl implements DateFormatter {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final int AMOUNT_OF_DAYS_TO_GO_BACK = 90;

    @Override
    public String getDateThreeMonthsBackFromNow() {

        Date dateThreeMonthsBefore = new DateTime(new Date())
                .minusDays(AMOUNT_OF_DAYS_TO_GO_BACK)
                .toDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

        return dateFormat.format(dateThreeMonthsBefore);
    }
}
