package com.wasp.landlordcommunication.views;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.chat.ChatActivity;
import com.wasp.landlordcommunication.views.home.HomeActivity;
import com.wasp.landlordcommunication.views.payments.PaymentsActivity;
import com.wasp.landlordcommunication.views.properties.PropertiesActivity;
import com.wasp.landlordcommunication.views.settings.SettingsActivity;

import butterknife.BindView;
import dagger.android.support.DaggerAppCompatActivity;

import static com.wasp.landlordcommunication.utils.Constants.CHAT_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.HOME_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.MY_PAYMENTS_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_FULL_NAME_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_TYPE_KEY;
import static com.wasp.landlordcommunication.utils.Constants.SETTINGS_DRAWER_ITEM_NAME;
import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity {

    @BindView(R.id.tb_drawer_toolbar)
    Toolbar mToolbar;

    private Drawer mDrawer;
    private AccountHeader mHeader;

    public BaseDrawerActivity() {
        //empty constructor required
    }

    public void setupDrawer() {


        mHeader = new AccountHeaderBuilder()
                .withActivity(BaseDrawerActivity.this)
                .withHeaderBackground(R.drawable.drawerheader)
                .withTranslucentStatusBar(true)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(getUserDrawerName())
                                .withSelectable(false)
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

    private Intent getNextIntent(long identifier) {

        Intent nextIntent;

        if (identifier == PropertiesActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, PropertiesActivity.class);
            return nextIntent;
        } else if (identifier == PaymentsActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, PaymentsActivity.class);
            return nextIntent;
        } else if (identifier == ChatActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, ChatActivity.class);
            return nextIntent;
        } else if (identifier == HomeActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, HomeActivity.class);
            return nextIntent;
        } else if (identifier == SettingsActivity.DRAWER_IDENTIFIER) {
            nextIntent = new Intent(this, SettingsActivity.class);
            return nextIntent;
        } else {
            return null;
        }
    }

    private String getPropertyDrawerItemName() {
        String userType = getUserType();

        if (userType.equals(Constants.TENANT)) {
            return Constants.MY_PLACES_DRAWER_ITEM_NAME;
        } else {
            return Constants.MY_PROPERTIES_DRAWER_ITEM_NAME;
        }
    }

    private String getUserType() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString(PREFERENCES_USER_TYPE_KEY, TENANT);
    }

    private String getUserDrawerName() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString(PREFERENCES_USER_FULL_NAME_KEY, "");
    }
}
