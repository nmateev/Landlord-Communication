package com.wasp.landlordcommunication.views.properties;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import butterknife.ButterKnife;

public class PropertiesActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 543;
    private String mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

        ButterKnife.bind(this);
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
        return null;
    }
}
