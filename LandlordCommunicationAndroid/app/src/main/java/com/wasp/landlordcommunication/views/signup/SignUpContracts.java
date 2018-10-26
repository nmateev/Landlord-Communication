package com.wasp.landlordcommunication.views.signup;

import com.wasp.landlordcommunication.models.User;

public interface SignUpContracts {


    interface View {

        void setPresenter(Presenter presenter);

        void showMessage(String message);

        void continueToNextRegistrationForm(String userName, String userPassword);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void continueRegistration(String userName, String userPassword, String userPasswordRepeat);
    }

    interface Navigator {

        void navigateToNextRegistrationForm(String userName, String userPassword);
    }
}
