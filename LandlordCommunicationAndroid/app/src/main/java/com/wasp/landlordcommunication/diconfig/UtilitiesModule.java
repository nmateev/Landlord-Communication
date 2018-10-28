package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.DateFormatter;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilitiesModule {

    @Provides
    public ImageEncoder imageEncoder() {
        return new com.wasp.landlordcommunication.utils.ImageEncoder();
    }

    @Provides
    public DateFormatter dateFormatter(@Named(Constants.DATE_REPRESENTATION_NAME) String dateRepresentation,
                                       @Named(Constants.TIME_ZONE_NAME) String timeZone) {

        return new com.wasp.landlordcommunication.utils.DateFormatter(dateRepresentation, timeZone);
    }

    @Provides
    @Named(Constants.DATE_REPRESENTATION_NAME)
    public String dateRepresentationForPaymentsDate() {
        return Constants.PAYMENTS_DATE_REPRESENTATION;
    }

    @Provides
    @Named(Constants.TIME_ZONE_NAME)
    public String dateTimeZone() {
        return Constants.TIME_ZONE;
    }
}
