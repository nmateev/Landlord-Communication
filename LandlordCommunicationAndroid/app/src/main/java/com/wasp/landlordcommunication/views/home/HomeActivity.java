package com.wasp.landlordcommunication.views.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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

public class HomeActivity extends BaseDrawerActivity implements HomeActivityContracts.Navigator {

    public static final long DRAWER_IDENTIFIER = 739;

    @Inject
    HomeFragment mHomeFragment;
    @Inject
    HomeActivityContracts.Presenter mHomeActivityPresenter;

    private String mUserType;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Intent incomingIntent = getIntent();
        User user = (User) incomingIntent.getSerializableExtra(USER_EXTRA);

        persistUserSessionData(user);
        mHomeActivityPresenter.setUserName(user.getUserName());
        mHomeActivityPresenter.setUserId(user.getUserId());

        mHomeFragment.setNavigator(this);
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
        SharedPreferences.Editor preferencesEditor = getPreferences(MODE_PRIVATE).edit();

        preferencesEditor.putInt(PREFERENCES_USER_ID_KEY, user.getUserId());
        preferencesEditor.putString(PREFERENCES_USER_NAME_KEY, user.getUserName());
        preferencesEditor.putString(PREFERENCES_USER_FULL_NAME_KEY, user.getFirstName() + " " + user.getLastName());
        preferencesEditor.putString(PREFERENCES_USER_TYPE_KEY, user.getUserType());
        preferencesEditor.apply();
    }
}
