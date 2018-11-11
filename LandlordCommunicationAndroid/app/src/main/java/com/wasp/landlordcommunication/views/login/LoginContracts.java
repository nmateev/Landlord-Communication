package com.wasp.landlordcommunication.views.login;

import com.wasp.landlordcommunication.models.User;

public interface LoginContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showLoginCredentialsProblemMessage(String message);

        void hideLoginProblemMessage();

        void showSignUpActivity();

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showHomeActivityWithUser(User user);

        void showMessage(String message);

        void showGoogleLoginActivity();

        void showUserTypeSelectionDialog();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void handleCustomLoginAttempt(String username, String password, int errorVisibilityCode);

        void handleLoginFieldFocusChange(int errorVisibilityCode);

        void handleSignUpButtonClick();

        void handleUnsuccessfulLogin();

        void handleCanceledLogin();

        void handleFacebookLogin(boolean isLoggedIn, User user);

        void handleSignWithGoogleButtonClick();

        void handleGoogleLogin(boolean isLoggedIn, User user);

        void handleUnselectedUserTypeOption();

        void handleChosenUserTypeOption(String userTypeOption);

        void checkAndHandleSocialLogin(User user);

        void checkErrorVisibility(int visibilityCode);
    }

    interface Navigator {
        void navigateToSignUpActivity();

        void navigateToHomeWithUser(User user);
    }
}
