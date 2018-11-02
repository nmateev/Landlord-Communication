package com.wasp.landlordcommunication.utils;

import com.wasp.landlordcommunication.models.CardTransaction;
import com.wasp.landlordcommunication.utils.base.Validator;

import java.util.Calendar;

public class CardTransactionValidator implements Validator<CardTransaction> {

    private static final int MIN_MONTH_VALUE = 1;
    private static final int MAX_MONTH_VALUE = 12;
    private static final int CARD_YEAR_EXPIRATION_SUBTRACT_VALUE = 5;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_CVV_NUMBER_MIN_VALUE = 100;
    private static final int CARD_CVV_NUMBER_MAX_VALUE = 999;

    @Override

    public void validateEntity(CardTransaction cardTransaction) throws ValidationException {
        if (cardTransaction.getFirstName().length() < Constants.MIN_NAME_LENGTH) {
            throw new ValidationException(Constants.NAME_FIELDS_ERROR_MESSAGE);
        }
        if (cardTransaction.getLastName().length() < Constants.MIN_NAME_LENGTH) {
            throw new ValidationException(Constants.NAME_FIELDS_ERROR_MESSAGE);
        }
        if (cardTransaction.getValidThruMonth() < MIN_MONTH_VALUE || cardTransaction.getValidThruMonth() > MAX_MONTH_VALUE) {
            throw new ValidationException(Constants.MONTH_VALIDATION_ERROR);
        }
        Calendar calendar = Calendar.getInstance();
        int minYearOfValidation = calendar.get(Calendar.YEAR);
        minYearOfValidation = minYearOfValidation - CARD_YEAR_EXPIRATION_SUBTRACT_VALUE;
        if (cardTransaction.getValidThruYear() < minYearOfValidation) {
            throw new ValidationException(Constants.CARD_EXPIRATION_YEAR_ERROR_MESSAGE);
        }
        if (cardTransaction.getCardNumber().length() < CARD_NUMBER_LENGTH) {
            throw new ValidationException(Constants.CARD_NUMBER_ERROR_MESSAGE);
        }
        if (cardTransaction.getCvvCardNumer() < CARD_CVV_NUMBER_MIN_VALUE || cardTransaction.getCvvCardNumer() > CARD_CVV_NUMBER_MAX_VALUE) {
            throw new ValidationException(Constants.CARD_CVV_NUMBER_ERROR_MESSAGE);
        }
    }
}
