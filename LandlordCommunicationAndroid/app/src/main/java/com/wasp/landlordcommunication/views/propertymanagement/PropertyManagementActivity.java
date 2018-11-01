package com.wasp.landlordcommunication.views.propertymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PropertyManagementActivity extends BaseDrawerActivity implements PropertyManagementContracts.Navigator {
    private static final int DRAWER_IDENTIFIER = -1;

    @Inject
    PropertyManagementFragment mPropertyManagementFragment;

    @Inject
    PropertyManagementContracts.Presenter mPresenter;

    private int mPropertyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_management);
        ButterKnife.bind(this);

        Intent incomingIntent = getIntent();
        mPropertyId = incomingIntent.getIntExtra(Constants.PROPERTY_ID_EXTRA, 0);
        mPresenter.setSelectedPropertyId(mPropertyId);

        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());

        mPropertyManagementFragment.setNavigator(this);
        mPropertyManagementFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_property_management, mPropertyManagementFragment)
                .commit();


    }

    @Override
    protected long getIdentifier() {
        //should not return valid identifier
        return DRAWER_IDENTIFIER;
    }
}
