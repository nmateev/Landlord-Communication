package com.wasp.landlordcommunication.views.signup.secondform;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.utils.Constants;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class SignUpSecondFormFragment extends Fragment implements SignUpSecondFromContracts.View, AdapterView.OnItemSelectedListener {

    @BindView(R.id.prb_loading)
    ProgressBar mProgressBarView;

    @BindView(R.id.sp_user_type_option)
    NiceSpinner mUserTypeSpinner;

    @BindView(R.id.met_first_name_field)
    MaterialTextField mFirstNameEditText;

    @BindView(R.id.met_last_name_field)
    MaterialTextField mLastNameEditText;

    @BindView(R.id.fab_register_button)
    FloatingActionButton mFinishRegistrationFloatingActionButton;

    private SignUpSecondFromContracts.Presenter mPresenter;
    private SignUpSecondFromContracts.Navigator mNavigator;

    private String[] mUserTypeOptions;
    private String mUserTypeOptionSelected;

    @Inject
    public SignUpSecondFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_second_form, container, false);
        ButterKnife.bind(this, view);

        mUserTypeOptions = getResources().getStringArray(R.array.user_type_options);
        mUserTypeSpinner.attachDataSource(Arrays.asList(mUserTypeOptions));
        mUserTypeOptionSelected = mUserTypeOptions[0];
        mUserTypeSpinner.setOnItemSelectedListener(this);

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
    public void setPresenter(SignUpSecondFromContracts.Presenter presenter) {
        mPresenter = presenter;
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
    public void showMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showHomeActivityWithUser(User user) {
        mNavigator.navigateToHomeWithUser(user);
    }


    public void setNavigator(SignUpSecondFromContracts.Navigator navigator) {
        mNavigator = navigator;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            mUserTypeOptionSelected = mUserTypeOptions[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mUserTypeOptionSelected = mUserTypeOptions[0];
    }

    @OnClick(R.id.fab_register_button)
    public void onFinishedRegistrationButtonClick() {

        String firstName = mFirstNameEditText.getEditText().getText().toString();
        String lastName = mLastNameEditText.getEditText().getText().toString();

        mPresenter.finishRegistration(mUserTypeOptionSelected, firstName, lastName);
    }
}
