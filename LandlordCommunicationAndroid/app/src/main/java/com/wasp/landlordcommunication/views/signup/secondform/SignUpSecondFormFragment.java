package com.wasp.landlordcommunication.views.signup.secondform;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUpSecondFormFragment extends Fragment implements SignUpSecondFromContracts.View {

    private SignUpSecondFromContracts.Presenter mPresenter;
    private SignUpSecondFromContracts.Navigator mNavigator;

    @BindView(R.id.prb_loading)
    ProgressBar mProgressBarView;

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

    public void setNavigator(SignUpSecondFromContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
