package com.wasp.landlordcommunication.views.properties;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PropertiesActivity extends BaseDrawerActivity implements PropertiesContracts.Navigator {

    public static final long DRAWER_IDENTIFIER = 543;
    @Inject
    PropertiesFragment mPropertiesFragment;
    @Inject
    PropertiesContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

        ButterKnife.bind(this);

        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());

        mPropertiesFragment.setNavigator(this);
        mPropertiesFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_properties, mPropertiesFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}
