package com.wasp.landlordcommunication.views.payments;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PaymentsActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 123;

    @Inject
    PaymentsFragment mPaymentsFragment;
    @Inject
    PaymentsContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        ButterKnife.bind(this);

        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());
        mPaymentsFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_payments, mPaymentsFragment)
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
