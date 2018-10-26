package com.wasp.landlordcommunication.views.signup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.wasp.landlordcommunication.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignUpFragment extends Fragment implements SignUpContracts.View {

    @BindView(R.id.met_username)
    MaterialTextField mUsernameEditText;

    @BindView(R.id.met_password_field)
    MaterialTextField mPasswordEditText;

    @BindView(R.id.met_password_field_repeat)
    MaterialTextField mRepeatPasswordEditText;

    @BindView(R.id.fab_continue_registration)
    FloatingActionButton mContinueFloatingActionButton;

    private SignUpContracts.Presenter mPresenter;
    private SignUpContracts.Navigator mNavigator;

    @Inject
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public void setPresenter(SignUpContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setNavigator(SignUpContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void continueToNextRegistrationForm(String userName, String userPassword) {
        mNavigator.navigateToNextRegistrationForm(userName, userPassword);
    }

    @OnClick(R.id.fab_continue_registration)
    public void onContinueRegistrationButtonClick() {

        String userName = mUsernameEditText.getEditText().getText().toString();
        String userPassword = mPasswordEditText.getEditText().getText().toString();
        String userPasswordRepeat = mRepeatPasswordEditText.getEditText().getText().toString();
        mPresenter.continueRegistration(userName, userPassword, userPasswordRepeat);
    }
}
