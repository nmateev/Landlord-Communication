package com.wasp.landlordcommunication.views.landlordpropertydetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.PROPERTY_ID_EXTRA;

public class LandlordPropertyDetailsActivity extends BaseDrawerActivity {
    public static final int DRAWER_IDENTIFIER = -1;

    @Inject
    LandlordPropertyDetailsFragment mLandlordPropertyDetailsFragment;

    @Inject
    LandlordPropertyDetailsContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_property_details);
        ButterKnife.bind(this);
        Intent incomingIntent = getIntent();

        int propertyId = incomingIntent.getIntExtra(PROPERTY_ID_EXTRA, 0);

        mPresenter.setSelectedPropertyId(propertyId);
        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());

        mLandlordPropertyDetailsFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_landlord_property_details, mLandlordPropertyDetailsFragment)
                .commit();

    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    protected long getIdentifier() {
        //should not return valid identifier because this activity is only accessed through LandlordsPropertiesListActivity
        return DRAWER_IDENTIFIER;
    }
}
