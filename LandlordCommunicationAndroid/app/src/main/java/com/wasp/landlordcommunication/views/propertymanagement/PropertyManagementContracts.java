package com.wasp.landlordcommunication.views.propertymanagement;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.models.Property;

public interface PropertyManagementContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showDefaultPropertyPicture();

        void showPropertyPicture(Bitmap image);

        void showPropertyDetails(Property property, String individualisation);

        void showPayButtonOption();

        void showRatingDialog();

        void showChatWithUsers(int userId, int otherUserId);

        void showPaymentInputOption();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void setSelectedPropertyId(int propertyId);

        void loadPropertyManagementOptions();

        void rateButtonIsClicked();

        void ratingWasCancelled();

        void userIsRated(int ratingValue);

        void messageButtonIsClicked();

        void payRentButtonIsClicked();

        void finishPaymentButtonIsClicked(String firstName, String lastName, String validThruMonth, String validThruYear, String cardNumber, String cardCvvNumber);
    }

    interface Navigator {


        void navigateToChatWithUsers(int userId, int otherUserId);
    }
}
