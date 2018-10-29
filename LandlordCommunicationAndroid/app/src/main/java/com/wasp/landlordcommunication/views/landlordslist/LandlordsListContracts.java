package com.wasp.landlordcommunication.views.landlordslist;

import com.wasp.landlordcommunication.models.User;

import java.util.List;

public interface LandlordsListContracts {
    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showNoLandlordsAvailableMessage(String message);

        void showLandlords(List<User> landlordsList);

        void showPropertiesForUser(User user);

        void clearLandlordsList();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void loadAllLandlords();

        void landlordIsSelected(User user);

        void filterLandlordsWithPattern(String pattern);
    }

    interface Navigator {

        void navigateToUsersProperties(User user);
    }

}
