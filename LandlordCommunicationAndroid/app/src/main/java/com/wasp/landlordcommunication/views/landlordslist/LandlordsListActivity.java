package com.wasp.landlordcommunication.views.landlordslist;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesListActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.USER_EXTRA;

public class LandlordsListActivity extends BaseDrawerActivity implements LandlordsListContracts.Navigator {
    public static final int DRAWER_IDENTIFIER = 213;

    @Inject
    LandlordsListFragment mLandlordsListFragment;
    @Inject
    LandlordsListContracts.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlords_list);
        ButterKnife.bind(this);

        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());

        mLandlordsListFragment.setNavigator(this);
        mLandlordsListFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_landlords_list, mLandlordsListFragment)
                .commit();


    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    @Override
    public void navigateToUsersProperties(User user) {
        user.setUserPicture(null);

        Intent intentToUsersProperties = new Intent(this, LandlordsPropertiesListActivity.class);
        intentToUsersProperties.putExtra(USER_EXTRA, user);
        startActivity(intentToUsersProperties);
    }
}
