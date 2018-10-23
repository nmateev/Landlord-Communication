package com.wasp.landlordcommunication.views.login;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

//TODO: Extend base drawer activity after the drawer activity is implemented
public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    LoginFragment mLoginFragment;
    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_login, mLoginFragment)
                .commit();
    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }
}
