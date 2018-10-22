package com.wasp.landlordcommunication.views.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.utils.Constants;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class LoginFragment extends Fragment implements LoginContracts.View {

    @BindView(R.id.et_username_field)
    EditText mUserNameEditText;
    @BindView(R.id.et_password_field)
    EditText mPasswordEditText;
    @BindView(R.id.tv_login_credentials_problem)
    TextView mLoginProblemTextView;
    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;
    private LoginContracts.Presenter mPresenter;
    private LoginContracts.Navigator mNavigator;

    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void setPresenter(LoginContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(LoginContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void showLoginCredentialsProblemMessage(String message) {
        mLoginProblemTextView.setVisibility(View.VISIBLE);
        mLoginProblemTextView.setText(message);
    }

    @Override
    public void hideLoginProblemMessage() {
        mLoginProblemTextView.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_custom_login)
    public void onLoginButtonClick() {
        mPresenter.handleCustomLoginAttempt(mUserNameEditText.getText().toString(),
                mPasswordEditText.getText().toString(),
                mLoginProblemTextView.getVisibility());
    }

    @OnClick(R.id.btn_custom_signup)
    public void onSignUpButtonClick() {
        mPresenter.handleSignUpButtonClick();
    }

    @OnClick(R.id.btn_fb_login)
    public void onFacebookLoginButtonClick(View view) {

    }

    @OnClick(R.id.btn_google_login)
    public void onGoogleLoginButtonClick(View view) {

    }

    @OnFocusChange(R.id.et_username_field)
    public void onUsernameFieldFocusChange() {
        mPresenter.handleLoginFieldFocusChange(mLoginProblemTextView.getVisibility());
    }

    @OnFocusChange(R.id.et_password_field)
    public void onPasswordFieldFocusChange() {
        mPresenter.handleLoginFieldFocusChange(mLoginProblemTextView.getVisibility());
    }

    @Override
    public void showSignUpActivity() {
        mNavigator.navigateToSignUpActivity();
    }

    @Override
    public void showProgressBar() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBarView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showHomeActivityWithUser(User user) {
        mNavigator.navigateToHomeWithUser(user);
    }
}
