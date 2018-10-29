package com.wasp.landlordcommunication.views.settings;

import android.content.Context;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import butterknife.ButterKnife;

public class SettingsActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 918;
    private String mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}
