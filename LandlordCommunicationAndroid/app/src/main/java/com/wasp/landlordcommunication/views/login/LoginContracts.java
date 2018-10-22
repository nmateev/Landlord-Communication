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
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void handleCustomLoginAttempt(String username, String password, int errorVisibilityCode);

        void handleLoginFieldFocusChange(int errorVisibilityCode);

        void handleSignUpButtonClick();
    }

    interface Navigator {
        void navigateToSignUpActivity();

        void navigateToHomeWithUser(User user);
    }
}
