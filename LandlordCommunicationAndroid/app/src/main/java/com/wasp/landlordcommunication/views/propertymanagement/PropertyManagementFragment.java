package com.wasp.landlordcommunication.views.propertymanagement;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.Arrays;
import java.util.Locale;

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


public class PropertyManagementFragment extends Fragment implements PropertyManagementContracts.View, RatingDialogListener {


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

    @BindView(R.id.tv_user_rating)
    TextView mUserRatingTextView;

    @BindView(R.id.btn_pay_rent)
    Button mPayRentButton;


    @BindView(R.id.tv_fill_credit_card_information)
    TextView mFillCreditCardInformationTextView;

    @BindView(R.id.et_first_name_card)
    EditText mFirstNameEditText;

    @BindView(R.id.et_last_name_card)
    EditText mLastNameEditText;

    @BindView(R.id.tv_valid_thru_message)
    TextView mValidThruTextView;

    @BindView(R.id.et_valid_thru_month)
    EditText mValidThruMonthEditText;

    @BindView(R.id.et_valid_thru_year)
    EditText mValidThruYearEditText;

    @BindView(R.id.et_card_number)
    EditText mCardNumberEditText;

    @BindView(R.id.et_card_cvv)
    EditText mCardCvvEditText;

    @BindView(R.id.btn_finish_payment)
    Button mFinishPaymentButton;

    @BindView(R.id.sv_property_management)
    ScrollView mPropertyManagementScrollView;

    @BindView(R.id.tv_change_amount_message)
    TextView mChangeRentPriceMessageTextView;

    @BindView(R.id.et_rent_new_price)
    EditText mRentNewPriceEditText;

    @BindView(R.id.btn_change_rent_amount)
    Button mChangeRentAmountButton;


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

     /*   mChatWithUserButton.setOnClickListener(this);
        mRateUserButton.setOnClickListener(this);
        mPayRentButton.setOnClickListener(this);*/

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

        mChangeRentPriceMessageTextView.setVisibility(View.VISIBLE);
        mRentNewPriceEditText.setVisibility(View.VISIBLE);
        mChangeRentAmountButton.setVisibility(View.VISIBLE);


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
        mFirstNameEditText.setVisibility(View.VISIBLE);
        mFillCreditCardInformationTextView.setVisibility(View.VISIBLE);
        mLastNameEditText.setVisibility(View.VISIBLE);
        mValidThruTextView.setVisibility(View.VISIBLE);
        mValidThruMonthEditText.setVisibility(View.VISIBLE);
        mValidThruYearEditText.setVisibility(View.VISIBLE);
        mCardNumberEditText.setVisibility(View.VISIBLE);
        mCardCvvEditText.setVisibility(View.VISIBLE);
        mFinishPaymentButton.setVisibility(View.VISIBLE);

        mPropertyManagementScrollView.post(() -> mPropertyManagementScrollView.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    public void showUserRating(double userRating) {
        String ratingRepresentation = String.format(Locale.UK, "%.1f", userRating) + Constants.RATING_REPRESENTATION;
        mUserRatingTextView.setVisibility(View.VISIBLE);
        mUserRatingTextView.setText(ratingRepresentation);
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

    @OnClick(R.id.btn_message)
    public void onMessageButtonClick() {
        mPresenter.messageButtonIsClicked();
    }

    @OnClick(R.id.btn_rate)
    public void onRateButtonClick() {
        mPresenter.rateButtonIsClicked();
    }

    @OnClick(R.id.btn_pay_rent)
    public void onPayButtonClick() {
        mPresenter.payRentButtonIsClicked();
    }

    @OnClick(R.id.btn_change_rent_amount)
    public void onChangeRentAmountButtonClick() {
        String newRentAmountInString = mRentNewPriceEditText.getText().toString();
        mPresenter.rentChangeButtonIsClicked(newRentAmountInString);
    }


    @OnClick(R.id.btn_finish_payment)
    public void onFinishPaymentButtonClick() {
        String firstName = mFirstNameEditText.getText().toString();
        String lastName = mLastNameEditText.getText().toString();
        String validThruMonth = mValidThruMonthEditText.getText().toString();
        String validThruYear = mValidThruYearEditText.getText().toString();
        String cardNumber = mCardNumberEditText.getText().toString();
        String cardCvvNumber = mCardCvvEditText.getText().toString();

        mPresenter.finishPaymentButtonIsClicked(firstName, lastName, validThruMonth, validThruYear, cardNumber, cardCvvNumber);
    }
}
