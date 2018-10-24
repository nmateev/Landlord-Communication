package com.wasp.landlordcommunication.views.home;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

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
        mUserType = user.getUserType();
        mUserName = user.getFirstName() + " " + user.getLastName();

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

    @Override
    protected String getUserType() {
        return mUserType;
    }

    @Override
    protected String getUserDrawerName() {
        return mUserName;
    }
}
