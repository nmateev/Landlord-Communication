package com.wasp.landlordcommunication.views.landlordpropertydetails;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.models.Property;

public interface LandlordPropertyDetailsContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showPropertyDetails(Property property);

        void showDefaultPropertyPicture();

        void showPropertyPicture(Bitmap propertyImage);

        void showRentButtonOption();

        void showDate(String date);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void setSelectedPropertyId(int propertyId);

        void loadPropertyDetails();

        void rentButtonIsClicked();
    }

}
