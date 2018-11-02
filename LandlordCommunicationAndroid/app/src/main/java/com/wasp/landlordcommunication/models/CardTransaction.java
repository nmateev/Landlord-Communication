package com.wasp.landlordcommunication.models;

public class CardTransaction {
    private String firstName;
    private String lastName;
    private int validThruMonth;
    private int validThruYear;
    private String cardNumber;
    private int cvvCardNumer;

    public CardTransaction() {

    }

    public CardTransaction(String firstName, String lastName, int validThruMonth, int validThruYear, String cardNumber, int cvvCardNumer) {
        setFirstName(firstName);
        setLastName(lastName);
        setValidThruMonth(validThruMonth);
        setValidThruYear(validThruYear);
        setCardNumber(cardNumber);
        setCvvCardNumer(cvvCardNumer);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getValidThruMonth() {
        return validThruMonth;
    }

    public int getValidThruYear() {
        return validThruYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvvCardNumer() {
        return cvvCardNumer;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setValidThruMonth(int validThruMonth) {
        this.validThruMonth = validThruMonth;
    }

    private void setValidThruYear(int validThruYear) {
        this.validThruYear = validThruYear;
    }

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    private void setCvvCardNumer(int cvvCardNumer) {
        this.cvvCardNumer = cvvCardNumer;
    }
}
