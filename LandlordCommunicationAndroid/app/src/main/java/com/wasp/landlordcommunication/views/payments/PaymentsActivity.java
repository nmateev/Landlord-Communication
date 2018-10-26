package com.wasp.landlordcommunication.views.payments;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PaymentsActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 123;
    private String mUserType;

    @Inject
    PaymentsFragment mPaymentsFragment;
    @Inject
    PaymentsPresenter mPaymentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        ButterKnife.bind(this);

        mPaymentsFragment.setPresenter(mPaymentsPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.payments_activity,mPaymentsFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}
