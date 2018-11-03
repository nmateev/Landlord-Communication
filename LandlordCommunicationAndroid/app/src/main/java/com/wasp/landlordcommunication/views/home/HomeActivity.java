package com.wasp.landlordcommunication.views.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_FULL_NAME_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_ID_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_NAME_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_TYPE_KEY;
import static com.wasp.landlordcommunication.utils.Constants.USER_EXTRA;
import static com.wasp.landlordcommunication.utils.Constants.USER_PROFILE_IMAGE_KEY;

public class HomeActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 739;

    @Inject
    HomeFragment mHomeFragment;
    @Inject
    HomeActivityContracts.Presenter mHomeActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Intent incomingIntent = getIntent();

        if (incomingIntent.hasExtra(USER_EXTRA)) {
            User user = (User) incomingIntent.getSerializableExtra(USER_EXTRA);
            persistUserSessionData(user);

        }
        mHomeActivityPresenter.setUserId(getUserId());
        mHomeActivityPresenter.setUserName(getUserName());
        mHomeActivityPresenter.setUserType(getUserType());

        mHomeFragment.setPresenter(mHomeActivityPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_home, mHomeFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    private void persistUserSessionData(User user) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFERENCES_USER_ID_KEY, user.getUserId());
        editor.putString(PREFERENCES_USER_NAME_KEY, user.getUserName());
        editor.putString(PREFERENCES_USER_FULL_NAME_KEY, user.getFirstName() + " " + user.getLastName());
        editor.putString(PREFERENCES_USER_TYPE_KEY, user.getUserType());
        editor.commit();
    }
}
