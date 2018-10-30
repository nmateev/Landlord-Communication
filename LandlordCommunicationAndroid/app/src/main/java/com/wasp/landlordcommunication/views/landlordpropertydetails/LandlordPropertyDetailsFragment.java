package com.wasp.landlordcommunication.views.landlordpropertydetails;


import android.graphics.Bitmap;
import android.graphics.Color;
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

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandlordPropertyDetailsFragment extends Fragment implements LandlordPropertyDetailsContracts.View {

    private static final float ALPHA_VALUE = 0.2f;
    private LandlordPropertyDetailsContracts.Presenter mPresenter;
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

    @BindView(R.id.tv_property_description)
    TextView mPropertyDescriptionTextView;

    @BindView(R.id.btn_rent_place)
    Button mRentPropertyButton;

    private FancyAlertDialog mRentConfirmationDialog;
    private FancyAlertDialog mRentSuccessfulDialog;

    @Inject

    public LandlordPropertyDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_property_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadPropertyDetails();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(LandlordPropertyDetailsContracts.Presenter presenter) {
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
    public void showPropertyDetails(Property property) {
        mPropertyAddressTextView.setVisibility(View.VISIBLE);
        mPropertyAddressTextView.setText(property.getPropertyAddress());

        mPropertyRentPriceTextView.setVisibility(View.VISIBLE);
        mPropertyRentPriceTextView.setText(String.valueOf(property.getRentPrice()));

        mPropertyDescriptionTextView.setVisibility(View.VISIBLE);
        mPropertyDescriptionTextView.setText(property.getDescription());
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
    public void showRentButtonOption() {
        mRentPropertyButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void fadeRentButton() {
        mRentPropertyButton.setAlpha(ALPHA_VALUE);
    }

    @Override
    public void showRentingConfirmationDialog() {
        mRentConfirmationDialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle(Constants.RENT_CONFIRMATION_DIALOG_TITLE)
                .setBackgroundColor(Color.parseColor(Constants.RENT_CONFIRMATION_DIALOG_COLOUR_STRING))
                .setMessage(Constants.RENT_CONFIRMATION_DIALOG_QUESTION_MESSAGE)
                .setNegativeBtnText(Constants.RENT_CONFIRMATION_DIALOG_CANCEL_OPTION_TEXT)
                .setPositiveBtnBackground(Color.parseColor(Constants.RENT_CONFIRMATION_DIALOG_COLOUR_STRING))
                .setPositiveBtnText(Constants.RENT_CONFIRMATION_DIALOG_CONFIRMATION_OPTION_TEXT)
                .setNegativeBtnBackground(Color.parseColor(Constants.RENT_CONFIRMATION_DIALOG_COLOUR_NEGATIVE_BUTTON_STRING))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_check_box_big, Icon.Visible)
                .OnPositiveClicked(() -> mPresenter.rentIsConfirmed())
                .OnNegativeClicked(() -> mPresenter.rentConfirmationIsCancelled())
                .build();


    }

    @Override
    public void showSuccessDialog() {
        mRentSuccessfulDialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle(Constants.SUCCESS)
                .setBackgroundColor(Color.parseColor(Constants.RENT_CONFIRMATION_DIALOG_COLOUR_STRING))
                .setMessage(Constants.MANAGE_PLACES_MESSAGE)
                .setPositiveBtnBackground(Color.parseColor(Constants.RENT_CONFIRMATION_DIALOG_COLOUR_STRING))
                .setPositiveBtnText(Constants.OK_TEXT)
                .setNegativeBtnBackground(Color.parseColor(Constants.RENT_CONFIRMATION_DIALOG_COLOUR_GREY_BUTTON_STRING))
                .setNegativeBtnText(Constants.RENT_CONFIRMATION_DIALOG_CANCEL_OPTION_TEXT)
                .setAnimation(Animation.POP)
                .setIcon(R.drawable.ic_check_box_big, Icon.Visible)
                .build();
    }

    @Override
    public void showDate(String date) {
        mPropertyRentDueDateTextView.setVisibility(View.VISIBLE);
        mPropertyRentDueDateTextView.setText(date);
    }


    @OnClick(R.id.btn_rent_place)
    public void onClick() {
        mPresenter.rentButtonIsClicked();
    }
}
