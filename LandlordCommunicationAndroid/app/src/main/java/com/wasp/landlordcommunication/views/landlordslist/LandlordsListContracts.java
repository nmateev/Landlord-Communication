package com.wasp.landlordcommunication.views.landlordslist;

import com.wasp.landlordcommunication.models.User;

public interface LandlordsListContracts {
    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void showAllLandlords();

        void landlordIsSelected(User user);
    }

    interface Navigator {

    }

}
