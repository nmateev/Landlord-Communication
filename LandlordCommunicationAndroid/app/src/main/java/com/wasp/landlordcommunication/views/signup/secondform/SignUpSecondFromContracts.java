package com.wasp.landlordcommunication.views.signup.secondform;

import com.wasp.landlordcommunication.models.User;

public interface SignUpSecondFromContracts {

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

        void setRegistrationInformation(String userName, String userPassword);
    }

    interface Navigator {

        void navigateToHomeWithUser(User user);
    }
}
