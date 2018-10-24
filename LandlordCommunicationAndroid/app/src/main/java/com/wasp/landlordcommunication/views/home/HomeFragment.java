package com.wasp.landlordcommunication.views.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements HomeActivityContracts.View {

    @BindView(R.id.tv_user_rating_value)
    TextView mUserRatingTextView;

    @BindView(R.id.iv_home_user_image)
    ImageView mUserPictureImageView;

    @BindView(R.id.tv_user_names)
    TextView mUserFullNameTextView;

    @BindView(R.id.tv_user_places_count)
    TextView mUserPlacesCountTextView;


    private HomeActivityContracts.Navigator mNavigator;
    private HomeActivityContracts.Presenter mPresenter;

    @Inject
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUserInformation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(HomeActivityContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showUserName(String name) {
        mUserFullNameTextView.setText(name);
    }

    @Override
    public void showUserRating(double rating) {
        String textRating = String.format(Locale.UK, "%.1f", rating) + "/5";
        mUserRatingTextView.setText(textRating);
    }

    public void setNavigator(HomeActivityContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}