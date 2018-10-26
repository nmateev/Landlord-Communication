package com.wasp.landlordcommunication.views.signup;

import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

public class SignUpPresenter implements SignUpContracts.Presenter {

    private SignUpContracts.View mView;

    @Inject
    public SignUpPresenter() {
    }

    @Override
    public void subscribe(SignUpContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void continueRegistration(String userName, String userPassword, String userPasswordRepeat) {
        if (userName.length() < Constants.MIN_LENGTH_VALUE ||
                userPassword.length() < Constants.MIN_LENGTH_VALUE ||
                userPasswordRepeat.length() < Constants.MIN_LENGTH_VALUE) {
            mView.showMessage(Constants.LOGIN_INVALID_FIELDS_MESSAGE);
        } else if (!userPassword.equals(userPasswordRepeat)) {
            mView.showMessage(Constants.PASSWORDS_MATCH_ERROR_MESSAGE);
        } else {
            mView.continueToNextRegistrationForm(userName, userPassword);
        }
    }
}
