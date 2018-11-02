package com.wasp.landlordcommunication.views.propertymanagement;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wasp.landlordcommunication.utils.Constants.FIVE_STAR_RATING_TEXT;
import static com.wasp.landlordcommunication.utils.Constants.FOUR_STAR_RATING_TEXT;
import static com.wasp.landlordcommunication.utils.Constants.ONE_STAR_RATING_TEXT;
import static com.wasp.landlordcommunication.utils.Constants.RENT_DUE_TITLE;
import static com.wasp.landlordcommunication.utils.Constants.THREE_STAR_RATING_TEXT;
import static com.wasp.landlordcommunication.utils.Constants.TWO_STAR_RATING_TEXT;


public class PropertyManagementFragment extends Fragment implements PropertyManagementContracts.View, RatingDialogListener, View.OnClickListener {


    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.iv_property_image)
    ImageView mPropertyImageView;

    @BindView(R.id.tv_property_address)
    TextView mPropertyAddressTextView;

    @BindView(R.id.tv_property_rent_price)
    TextView mPropertyRentPriceTextView;

    @BindView(R.id.tv_property_rent_due_date)
    TextView mPropertyRentDueDateTextView;

    @BindView(R.id.btn_message)
    Button mChatWithUserButton;

    @BindView(R.id.btn_rate)
    Button mRateUserButton;

    @BindView(R.id.tv_or_option_text)
    TextView mOrMessageTextView;

    @BindView(R.id.tv_user_individualisation)
    TextView mUserIndividualisationTextView;

    @BindView(R.id.btn_pay_rent)
    Button mPayRentButton;


    private PropertyManagementContracts.Navigator mNavigator;
    private PropertyManagementContracts.Presenter mPresenter;


    @Inject
    public PropertyManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_property_management, container, false);
        ButterKnife.bind(this, view);

        mChatWithUserButton.setOnClickListener(this);
        mRateUserButton.setOnClickListener(this);
        mPayRentButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadPropertyManagementOptions();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(PropertyManagementContracts.Presenter presenter) {
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
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showDefaultPropertyPicture() {
        mPropertyImageView.setImageResource(R.drawable.defaultpropertypicture);
    }

    @Override
    public void showPropertyPicture(Bitmap propertyImage) {
        mPropertyImageView.setImageBitmap(propertyImage);
    }


    @Override
    public void showPropertyDetails(Property property, String individualisation) {
        mPropertyAddressTextView.setVisibility(View.VISIBLE);
        mPropertyAddressTextView.setText(property.getPropertyAddress());

        mPropertyRentPriceTextView.setVisibility(View.VISIBLE);
        mPropertyRentPriceTextView.setText(String.valueOf(property.getRentPrice()));

        String date = RENT_DUE_TITLE + property.getDueDate();
        mPropertyRentDueDateTextView.setVisibility(View.VISIBLE);
        mPropertyRentDueDateTextView.setText(date);

        mChatWithUserButton.setVisibility(View.VISIBLE);
        mRateUserButton.setVisibility(View.VISIBLE);
        mOrMessageTextView.setVisibility(View.VISIBLE);
        mUserIndividualisationTextView.setVisibility(View.VISIBLE);

        mUserIndividualisationTextView.setText(new StringBuilder()
                .append(Constants.INDIVIDUALISATION)
                .append(individualisation)
                .toString());


    }

    @Override
    public void showPayButtonOption() {
        mPayRentButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText(Constants.SUBMIT_OPTION)
                .setNegativeButtonText(Constants.CANCEL_OPTION)
                .setNoteDescriptions(Arrays.asList(ONE_STAR_RATING_TEXT, TWO_STAR_RATING_TEXT, THREE_STAR_RATING_TEXT, FOUR_STAR_RATING_TEXT, FIVE_STAR_RATING_TEXT))
                .setDefaultRating(1)
                .setTitle(Constants.RATE_DIALOG_TITLE_MESSAGE)
                .setDescription(Constants.RATE_DIALOG_DESCRIPTION_MESSAGE)
                .setCommentInputEnabled(false)
                .setStarColor(R.color.colorAccent)
                .setTitleTextColor(R.color.colorAccent)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .create(getActivity())
                .setTargetFragment(this, 0)
                .show();
    }

    @Override
    public void showChatWithUsers(int userId, int otherUserId) {
        mNavigator.navigateToChatWithUsers(userId, otherUserId);
    }

    @Override
    public void showPaymentInputOption() {

    }

    public void setNavigator(PropertyManagementContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onNegativeButtonClicked() {
        mPresenter.ratingWasCancelled();
    }

    @Override
    public void onNeutralButtonClicked() {
        mPresenter.ratingWasCancelled();
    }

    @Override
    public void onPositiveButtonClicked(int ratingValue, String s) {
        mPresenter.userIsRated(ratingValue);
    }

    @Override
    public void onClick(View buttonView) {
        switch (buttonView.getId()) {
            case R.id.btn_message:
                mPresenter.messageButtonIsClicked();
                break;
            case R.id.btn_rate:
                mPresenter.rateButtonIsClicked();
                break;
            case R.id.btn_pay_rent:
                mPresenter.payRentButtonIsClicked();
                break;
            default:
                break;
        }
    }
}
