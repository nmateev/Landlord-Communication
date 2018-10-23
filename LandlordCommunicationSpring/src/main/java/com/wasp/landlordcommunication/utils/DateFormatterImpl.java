package com.wasp.landlordcommunication.utils;

import org.joda.time.LocalDateTime;
import java.util.Date;

public class DateFormatterImpl implements DateFormatter {
    private static final int AMOUNT_OF_DAYS_TO_GO_BACK = 90;

    @Override
    public Date getDateThreeMonthsBackFromNow() {
        LocalDateTime dateThreeMonthsBackFromNow = LocalDateTime.now().minusDays(AMOUNT_OF_DAYS_TO_GO_BACK);
        return dateThreeMonthsBackFromNow.toDate();
    }
}
