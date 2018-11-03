package com.wasp.landlordcommunication.views.settings;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SettingsActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 918;

    @Inject
    SettingsFragment mSettingsFragment;

    @Inject
    SettingsContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
        mPresenter.setUserType(getUserType());

        mSettingsFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_settings, mSettingsFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}
