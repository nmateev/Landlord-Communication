package com.wasp.landlordcommunication.views.login;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import es.dmoral.toasty.Toasty;

import static com.wasp.landlordcommunication.utils.Constants.INITIAL_SELECTION;
import static com.wasp.landlordcommunication.utils.Constants.SELECT_USER_TYPE_TITLE;


public class LoginFragment extends Fragment implements LoginContracts.View, GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_CODE = 999;
    @BindView(R.id.et_username_field)
    EditText mUserNameEditText;
    @BindView(R.id.et_password_field)
    EditText mPasswordEditText;
    @BindView(R.id.tv_login_credentials_problem)
    TextView mLoginProblemTextView;
    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;
    @BindView(R.id.btn_fb_login)
    LoginButton mFacebookButton;

    private AccessToken mAccessToken;
    private CallbackManager mCallbackManager;
    private GoogleApiClient mGoogleApiClient;
    private LoginContracts.Presenter mPresenter;
    private LoginContracts.Navigator mNavigator;
    private int item;


    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        setupGoogleLogin();
        setupFacebookLogin();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
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


    @Override
    public void showProgressBar() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBarView.setVisibility(View.GONE);
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

    @OnClick(R.id.btn_google_login)
    public void onGoogleLoginButtonClick() {

        mPresenter.handleSignWithGoogleButtonClick();
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
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showHomeActivityWithUser(User user) {
        mNavigator.navigateToHomeWithUser(user);
    }

    @Override
    public void showMessage(String message) {

        Toast
                .makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showGoogleLoginActivity() {

        Intent intentToGoogleSignIn = Auth
                .GoogleSignInApi
                .getSignInIntent(mGoogleApiClient);
        startActivityForResult(intentToGoogleSignIn, REQUEST_CODE);

    }

    @Override
    public void showUserTypeSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(SELECT_USER_TYPE_TITLE);
        String[] userTypeOptions = getResources().getStringArray(R.array.user_types);

        builder.setSingleChoiceItems(userTypeOptions, INITIAL_SELECTION, (dialog, selected) -> item = selected);
        builder.setPositiveButton(Constants.CONFIRMATION, (dialog, chosen) -> mPresenter.handleChosenUserTypeOption(userTypeOptions[item]));
        builder.setNegativeButton(Constants.DISAGREEMENT, (dialogInterface, i) -> mPresenter.handleUnselectedUserTypeOption());

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleLoginResult(googleSignInResult);
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mPresenter.handleUnsuccessfulLogin();
    }

    private void setupFacebookLogin() {
        mFacebookButton.setFragment(this);
        mFacebookButton.setReadPermissions(Constants.EMAIL_AND_PUBLIC_PROFILE);
        mCallbackManager = CallbackManager.Factory.create();
        mFacebookButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mFacebookButton.setReadPermissions(Constants.EMAIL_AND_PUBLIC_PROFILE);
                mAccessToken = loginResult.getAccessToken();
                boolean isLoggedIn = mAccessToken != null && !mAccessToken.isExpired();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    Profile currentProfile = Profile.getCurrentProfile();
                    if (!Objects.equals(currentProfile, null)) {
                        String firstName = currentProfile.getFirstName();
                        String lastName = currentProfile.getLastName();
                        User user = new User(firstName + lastName, firstName, lastName);
                        mPresenter.handleFacebookLogin(isLoggedIn, user);
                    }
                });
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                mPresenter.handleCanceledLogin();
            }

            @Override
            public void onError(FacebookException error) {
                mPresenter.handleUnsuccessfulLogin();
            }
        });
    }

    private void setupGoogleLogin() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient
                .Builder(Objects.requireNonNull(getContext()))
                .enableAutoManage(Objects.requireNonNull(getActivity()), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    private void handleLoginResult(GoogleSignInResult googleSignInResult) {
        boolean isGoogleLoginSuccessful = googleSignInResult.isSuccess();
        User user = new User();
        if (isGoogleLoginSuccessful) {
            String email = googleSignInResult.getSignInAccount().getEmail();
            String displayName = googleSignInResult.getSignInAccount().getDisplayName();
            String familyName = googleSignInResult.getSignInAccount().getFamilyName();

            user = new User(email, displayName, familyName);
        }
        mPresenter.handleGoogleLogin(isGoogleLoginSuccessful, user);
    }
}
