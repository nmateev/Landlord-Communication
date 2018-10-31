package com.wasp.landlordcommunication.views.properties;

public interface PropertiesContracts {

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

    }

    interface Navigator {

    }
}
