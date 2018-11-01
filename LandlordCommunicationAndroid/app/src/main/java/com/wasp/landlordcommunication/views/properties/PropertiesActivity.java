package com.wasp.landlordcommunication.views.properties;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;
import com.wasp.landlordcommunication.views.propertymanagement.PropertyManagementActivity;

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

    @Override
    public void navigateToPropertyManagementOptions(int propertyId) {

        Intent intent = new Intent(this, PropertyManagementActivity.class);
        intent.putExtra(Constants.PROPERTY_ID_EXTRA, propertyId);
        startActivity(intent);
    }
}
