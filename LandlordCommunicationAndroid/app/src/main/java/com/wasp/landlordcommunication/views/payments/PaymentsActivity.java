package com.wasp.landlordcommunication.views.payments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_ID_KEY;
import static com.wasp.landlordcommunication.utils.Constants.PREFERENCES_USER_TYPE_KEY;

public class PaymentsActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 123;

    @Inject
    PaymentsFragment mPaymentsFragment;
    @Inject
    PaymentsContracts.Presenter mPresenter;
    private String mUserType;
    private int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        ButterKnife.bind(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mUserType = preferences.getString(PREFERENCES_USER_TYPE_KEY, "");
        mUserId = preferences.getInt(PREFERENCES_USER_ID_KEY, 0);

        mPresenter.setUserId(mUserId);
        mPresenter.setUserType(mUserType);
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
