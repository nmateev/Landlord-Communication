package com.wasp.landlordcommunication.views;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.repositories.base.BitmapCacheRepository;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.ImageEncoder;
import com.wasp.landlordcommunication.views.chat.ChatActivity;
import com.wasp.landlordcommunication.views.home.HomeActivity;
import com.wasp.landlordcommunication.views.landlordslist.LandlordsListActivity;
import com.wasp.landlordcommunication.views.payments.PaymentsActivity;
import com.wasp.landlordcommunication.views.properties.PropertiesActivity;
import com.wasp.landlordcommunication.views.settings.SettingsActivity;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import dagger.android.support.DaggerAppCompatActivity;

import static com.wasp.landlordcommunication.utils.Constants.CHAT_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.HOME_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.LANDLORDS_LIST_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.MY_PAYMENTS_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_FULL_NAME_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_ID_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_NAME_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_TYPE_KEY;
import static com.wasp.landlordcommunication.utils.Constants.SETTINGS_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.TENANT;
import static com.wasp.landlordcommunication.utils.Constants.USER_PROFILE_IMAGE_KEY;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity {

    @BindView(R.id.tb_drawer_toolbar)
    Toolbar mToolbar;

    private Drawer mDrawer;
    private AccountHeader mHeader;
    private SharedPreferences mPreferences;

    @Inject
    BitmapCacheRepository mBitmapCacheRepository;

    @Inject
    com.wasp.landlordcommunication.utils.base.ImageEncoder mImageEncoder;

    public BaseDrawerActivity() {
        //empty constructor required
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDrawer.closeDrawer();
    }

    public void setupDrawer() {


        mHeader = new AccountHeaderBuilder()
                .withActivity(BaseDrawerActivity.this)
                .withHeaderBackground(R.drawable.drawerheader)
                .withTranslucentStatusBar(true)
                .withSelectionListEnabledForSingleProfile(false)
                .withCompactStyle(true)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(getUserDrawerName())
                                .withIcon(getUserProfileImage())
                                .withSelectable(true)
                                .withNameShown(true)
                )
                .build();


        PrimaryDrawerItem home = new PrimaryDrawerItem()
                .withIdentifier(HomeActivity.DRAWER_IDENTIFIER)
                .withName(HOME_DRAWER_ITEM_NAME);
        SecondaryDrawerItem myProperties = new SecondaryDrawerItem()
                .withIdentifier(PropertiesActivity.DRAWER_IDENTIFIER)
                .withName(getPropertyDrawerItemName());
        SecondaryDrawerItem myPayments = new SecondaryDrawerItem()
                .withIdentifier(PaymentsActivity.DRAWER_IDENTIFIER)
                .withName(MY_PAYMENTS_DRAWER_ITEM_NAME);
        SecondaryDrawerItem landlordsList = new SecondaryDrawerItem()
                .withIdentifier(LandlordsListActivity.DRAWER_IDENTIFIER)
                .withName(LANDLORDS_LIST_DRAWER_ITEM_NAME);
        SecondaryDrawerItem chat = new SecondaryDrawerItem()
                .withIdentifier(ChatActivity.DRAWER_IDENTIFIER)
                .withName(CHAT_DRAWER_ITEM_NAME);
        SecondaryDrawerItem settings = new SecondaryDrawerItem()
                .withIdentifier(SettingsActivity.DRAWER_IDENTIFIER)
                .withName(SETTINGS_DRAWER_ITEM_NAME);


        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .withAccountHeader(mHeader)
                .withCloseOnClick(true)
                .addDrawerItems(
                        home.withIcon(R.drawable.ic_home),
                        new DividerDrawerItem(),
                        myProperties.withIcon(R.drawable.propertiesicon),
                        new DividerDrawerItem(),
                        myPayments.withIcon(R.drawable.paymentsicon),
                        new DividerDrawerItem(),
                        landlordsList.withIcon(R.drawable.ic_landlords_list),
                        new DividerDrawerItem(),
                        chat.withIcon(R.drawable.chaticon),
                        new DividerDrawerItem(),
                        settings.withIcon(R.drawable.ic_settings),
                        new DividerDrawerItem()

                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long identifier = drawerItem.getIdentifier();
                    if (getIdentifier() == identifier) {
                        return false;
                    }
                    Intent intent = getNextIntent(identifier);
                    if (intent == null) {
                        return false;
                    }
                    startActivity(intent);
                    return true;
                })
                .build();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected abstract long getIdentifier();

    protected int getUserId() {
        return mPreferences.getInt(PREFERENCES_USER_ID_KEY, 0);
    }

    protected String getUserName() {
        return mPreferences.getString(PREFERENCES_USER_NAME_KEY, Constants.EMPTY_STRING);
    }

    protected String getUserType() {
        return mPreferences.getString(PREFERENCES_USER_TYPE_KEY, TENANT);
    }

    protected String getUserDrawerName() {
        return mPreferences.getString(PREFERENCES_USER_FULL_NAME_KEY, Constants.EMPTY_STRING);
    }

    private String getPropertyDrawerItemName() {
        String userType = getUserType();

        if (userType.equals(Constants.TENANT)) {
            return Constants.MY_PLACES_DRAWER_ITEM_NAME;
        } else {
            return Constants.MY_PROPERTIES_DRAWER_ITEM_NAME;
        }
    }

    private Intent getNextIntent(long identifier) {
        if (identifier == HomeActivity.DRAWER_IDENTIFIER) {
            return new Intent(this, HomeActivity.class);
        } else if (identifier == PropertiesActivity.DRAWER_IDENTIFIER) {
            return new Intent(this, PropertiesActivity.class);
        } else if (identifier == PaymentsActivity.DRAWER_IDENTIFIER) {
            return new Intent(this, PaymentsActivity.class);
        } else if (identifier == LandlordsListActivity.DRAWER_IDENTIFIER) {
            return new Intent(this, LandlordsListActivity.class);
        } else if (identifier == ChatActivity.DRAWER_IDENTIFIER) {
            return new Intent(this, ChatActivity.class);
        } else if (identifier == SettingsActivity.DRAWER_IDENTIFIER) {
            return new Intent(this, SettingsActivity.class);
        } else {
            return null;
        }
    }

    private Bitmap getUserProfileImage() {

        //initial check to handle the first try to get the user image at initial navigation to home it will not be loaded in lru cache
        Bitmap userImage = mBitmapCacheRepository.getBitmapFromCache(Constants.USER_PROFILE_IMAGE_KEY);

        if (Objects.equals(userImage, null)) {

            String imageInString = mPreferences.getString(USER_PROFILE_IMAGE_KEY, "");
            if (Objects.equals(imageInString, null) || imageInString.equals("")) {
                return BitmapFactory.decodeResource(getResources(),
                        R.drawable.defaultuserpicture);
            } else {
                Bitmap userProfileImage = mImageEncoder.decodeStringToBitmap(imageInString);
                if (!Objects.equals(userProfileImage, null)) {
                    mBitmapCacheRepository.addBitmapToBitmapCache(userProfileImage, USER_PROFILE_IMAGE_KEY);
                    return mBitmapCacheRepository.getBitmapFromCache(USER_PROFILE_IMAGE_KEY);
                } else {
                    BitmapFactory.decodeResource(getResources(),
                            R.drawable.defaultuserpicture);
                }
            }

            return BitmapFactory.decodeResource(getResources(),
                    R.drawable.defaultuserpicture);
        } else {
            return userImage;
        }
    }
}
