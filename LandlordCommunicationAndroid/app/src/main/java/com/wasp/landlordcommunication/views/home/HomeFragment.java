package com.wasp.landlordcommunication.views.home;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.NotificationReceiver;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.ALARM_SERVICE;


public class HomeFragment extends Fragment implements HomeActivityContracts.View {
    private static final int GALLERY_IMAGE_CHOOSER_REQUEST_CODE = 5;
    private static final int TAKE_PICTURE_REQUEST_CODE = 10;

    @BindView(R.id.tv_user_rating_value)
    TextView mUserRatingTextView;

    @BindView(R.id.iv_home_user_image)
    ImageView mUserPictureImageView;

    @BindView(R.id.tv_user_names)
    TextView mUserFullNameTextView;

    @BindView(R.id.tv_user_places_count)
    TextView mUserPlacesCountTextView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.fab_change_picture)
    FloatingActionButton mChangePictureFloatingActionButton;

    @BindView(R.id.fab_take_picture)
    FloatingActionButton mTakePictureFloatingActionButton;

    @BindView(R.id.fam_image_options_menu)
    FloatingActionsMenu mImageChangeFloatingMenu;

    private HomeActivityContracts.Presenter mPresenter;
    private AlarmManager mAlarmManager;

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
        mAlarmManager = (AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(ALARM_SERVICE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUserInformation();
        mPresenter.updatePropertiesPaidStatus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(HomeActivityContracts.Presenter presenter) {
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
    public void showUserName(String name) {
        mUserFullNameTextView.setText(name);
    }

    @Override
    public void showUserRating(double rating) {
        String ratingRepresentation = String.format(Locale.UK, "%.1f", rating) + Constants.RATING_REPRESENTATION;
        mUserRatingTextView.setText(ratingRepresentation);
    }

    @OnClick(R.id.fab_change_picture)
    public void onSelectPictureFromGalleryButtonClick() {
        mImageChangeFloatingMenu.collapse();
        mPresenter.selectPictureFromGalleryButtonClickIsClicked();
    }

    @OnClick(R.id.fab_take_picture)
    public void onTakePictureButtonClick() {
        mImageChangeFloatingMenu.collapse();
        mPresenter.takePictureButtonIsClicked();
    }

    @Override
    public void presentOptionToTakePicture() {
        Intent intentToTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentToTakePicture, TAKE_PICTURE_REQUEST_CODE);
    }

    @Override
    public void showOptionToChooseImage() {
        Intent intentToChooseImage = new Intent(Intent.ACTION_GET_CONTENT);
        intentToChooseImage.setType(Constants.IMAGE_FILE_TYPE);
        startActivityForResult(intentToChooseImage, GALLERY_IMAGE_CHOOSER_REQUEST_CODE);

    }

    @Override
    public void showUserImage(Bitmap userImage) {

        mUserPictureImageView.setImageBitmap(userImage);
    }

    @Override
    public void setupRentNotification(String channelName, int notificationCode, String notificationTitle, String notificationDescription, String notificationRentAddress, int dayRentIsDue) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        //setting the calendar as if it is the day of the rent
        calendar.set(year, month, dayRentIsDue, Constants.RENT_NOTIFICATION_HOUR_TRIGGER, Constants.RENT_NOTIFICATION_MINUTE_TRIGGER);

        //subtract 5 days from the due date in order to setup the notification 5 days before the actual due date
        calendar.add(Calendar.DATE, Constants.RENT_NOTIFICATION_DAYS_BEFORE_PERIOD);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
            Intent intent = new Intent(getContext(), NotificationReceiver.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra(Constants.PURPOSE_EXTRA, channelName);
            intent.putExtra(Constants.NOTIFICATION_CODE_EXTRA, notificationCode);
            intent.putExtra(Constants.NOTIFICATION_TITLE_EXTRA, notificationTitle);
            intent.putExtra(Constants.NOTIFICATION_DESCRIPTION_EXTRA, notificationDescription);
            intent.putExtra(Constants.NOTIFICATION_DESCRIPTION_ADDRESS_EXTRA, notificationRentAddress);

            PendingIntent pendingNotificationIntent = PendingIntent
                    .getBroadcast(getContext(), notificationCode, intent, 0);
            Objects
                    .requireNonNull(mAlarmManager)
                    .setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingNotificationIntent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (!Objects.equals(data.getData(), null)) {
                Uri uri = data.getData();
                Bitmap image;
                try {
                    image = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), uri);
                    mPresenter.newImageIsChosen(image);
                } catch (IOException e) {
                    mPresenter.errorOccurredOnChangingPicture();
                }
            } else {
                mPresenter.errorOccurredOnChangingPicture();
            }
        } else {
            mPresenter.errorOccurredOnChangingPicture();
        }


        if (requestCode == TAKE_PICTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if ((!Objects.equals(data, null)) && (!Objects.equals(data.getExtras(), null))) {
                Bitmap image;
                try {
                    image = (Bitmap) Objects.requireNonNull(data.getExtras()).get(Constants.DATA_EXTRA);
                    mPresenter.newImageIsChosen(image);
                } catch (NullPointerException npe) {
                    mPresenter.errorOccurredOnChangingPicture();
                }
            } else {
                mPresenter.errorOccurredOnChangingPicture();
            }
        } else {
            mPresenter.errorOccurredOnChangingPicture();
        }
    }
}
