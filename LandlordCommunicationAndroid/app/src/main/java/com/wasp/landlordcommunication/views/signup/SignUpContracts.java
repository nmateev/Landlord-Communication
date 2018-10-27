package com.wasp.landlordcommunication.views.signup;

public interface SignUpContracts {


    interface View {

        void setPresenter(Presenter presenter);

        void showMessage(String message);

        void continueToNextRegistrationForm(String userName, String userPassword);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showUserNameIsTaken();

        void hideUserNameIsTakenMessage();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void continueRegistration(String userName, String userPassword, String userPasswordRepeat,boolean isUserNameTaken);

        void checkIfUsernameIsAvailable(String userName);

    }

    interface Navigator {

        void navigateToNextRegistrationForm(String userName, String userPassword);
    }
}
