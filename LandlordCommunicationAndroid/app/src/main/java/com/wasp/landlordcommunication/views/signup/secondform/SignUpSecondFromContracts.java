package com.wasp.landlordcommunication.views.signup.secondform;

import com.wasp.landlordcommunication.models.User;

public interface SignUpSecondFromContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showHomeActivityWithUser(User user);

    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setRegistrationInformation(String userName, String userPassword);

        void finishRegistration(String userTypeOption, String firstName, String lastName);

    }

    interface Navigator {

        void navigateToHomeWithUser(User user);
    }
}
