package com.wasp.landlordcommunication.views.signup.secondform;

import javax.inject.Inject;

public class SignUpSecondFromPresenter implements SignUpSecondFromContracts.Presenter {

    private SignUpSecondFromContracts.View mView;
    String mUserName;
    String mUserPassword;

    @Inject
    public SignUpSecondFromPresenter() {

    }

    @Override
    public void subscribe(SignUpSecondFromContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setRegistrationInformation(String userName, String userPassword) {
        mUserName = userName;
        mUserPassword = userPassword;
    }
}
