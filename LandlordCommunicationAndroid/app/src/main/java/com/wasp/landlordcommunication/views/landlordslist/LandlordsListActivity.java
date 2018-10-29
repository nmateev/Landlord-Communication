package com.wasp.landlordcommunication.views.landlordslist;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

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

}
