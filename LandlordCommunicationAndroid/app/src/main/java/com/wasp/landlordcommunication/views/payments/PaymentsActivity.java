package com.wasp.landlordcommunication.views.payments;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import butterknife.ButterKnife;

public class PaymentsActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 123;
    private String mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

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
