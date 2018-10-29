package com.wasp.landlordcommunication.views.landlordspropertieslist;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.USER_EXTRA;

public class LandlordsPropertiesListActivity extends BaseDrawerActivity implements LandlordsPropertiesListContracts.Navigator {
    public static final int DRAWER_IDENTIFIER = -1;

    @Inject
    LandlordsPropertiesListFragment mLandlordsPropertiesListFragment;
    @Inject
    LandlordsPropertiesListContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlords_properties_list);
        ButterKnife.bind(this);

        Intent incomingIntent = getIntent();

        User selectedLandlord = (User) incomingIntent.getSerializableExtra(USER_EXTRA);

        mPresenter.setSelectedLandlord(selectedLandlord);
        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());

        mLandlordsPropertiesListFragment.setNavigator(this);
        mLandlordsPropertiesListFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_landlords_properties_list, mLandlordsPropertiesListFragment)
                .commit();

    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    protected long getIdentifier() {
        //should not return valid identifier because this activity is only accessed through LandlordsListActivity
        return DRAWER_IDENTIFIER;
    }
}
