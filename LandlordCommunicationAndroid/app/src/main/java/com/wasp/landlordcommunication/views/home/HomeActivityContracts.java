package com.wasp.landlordcommunication.views.home;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.models.User;

public interface HomeActivityContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showUserName(String name);

        void showUserRating(double rating);

        void showOptionToChooseImage();

        void presentOptionToTakePicture();

        void showUserImage(Bitmap userImage);

        void setupRentNotification(String channelName, int notificationCode, String notificationTitle, String notificationDescription, String notificationRentAddress, int dayRentIsDue);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadUserInformation();

        void loadUserPictureAndName();

        void loadUserRating();

        void setUserName(String userName);

        void setUserId(int userId);

        void selectPictureFromGalleryButtonClickIsClicked();

        void takePictureButtonIsClicked();

        void newImageIsChosen(Bitmap image);

        void updateUserPicture(User user, String imageString);

        void errorOccurredOnChangingPicture();

        void decodeImageAndPresentToView(String userPicture, String errorMessage);

        void updatePropertiesPaidStatus();

        void setUserType(String userType);
    }
}
