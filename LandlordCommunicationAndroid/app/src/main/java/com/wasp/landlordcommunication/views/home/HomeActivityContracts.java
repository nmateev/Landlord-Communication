package com.wasp.landlordcommunication.views.home;

public interface HomeActivityContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showUserName(String name);

        void showUserRating(double rating);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadUserInformation();

        void loadUserPictureAndName();

        void loadUserRating();

        void setUserName(String userName);

        void setUserId(int userId);
    }

    interface Navigator {


    }
}
