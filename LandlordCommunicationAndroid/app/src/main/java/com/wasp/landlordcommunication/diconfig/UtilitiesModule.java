package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.models.CardTransaction;
import com.wasp.landlordcommunication.utils.CardTransactionValidator;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.DateFormatter;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;
import com.wasp.landlordcommunication.utils.base.Validator;

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
    public DateFormatter dateFormatter(@Named(Constants.DATE_REPRESENTATION_NAME) String dateRepresentation) {

        return new com.wasp.landlordcommunication.utils.DateFormatter(dateRepresentation);
    }

    @Provides
    @Named(Constants.DATE_REPRESENTATION_NAME)
    public String dateRepresentationForPaymentsDate() {
        return Constants.PAYMENTS_DATE_REPRESENTATION;
    }

    @Provides
    public Validator<CardTransaction> cardTransactionValidator() {
        return new CardTransactionValidator();
    }
}
