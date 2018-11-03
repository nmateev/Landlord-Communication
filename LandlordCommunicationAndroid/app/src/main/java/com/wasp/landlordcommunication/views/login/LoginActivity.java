package com.wasp.landlordcommunication.views.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.views.home.HomeActivity;
import com.wasp.landlordcommunication.views.signup.SignUpActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static com.wasp.landlordcommunication.utils.Constants.USER_EXTRA;
import static com.wasp.landlordcommunication.utils.Constants.USER_PROFILE_IMAGE_KEY;


public class LoginActivity extends DaggerAppCompatActivity implements LoginContracts.Navigator {

    @Inject
    LoginFragment mLoginFragment;
    @Inject
    LoginContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginFragment.setNavigator(this);
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

    @Override
    public void navigateToSignUpActivity() {

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToHomeWithUser(User user) {
        Intent intent = new Intent(this, HomeActivity.class);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PROFILE_IMAGE_KEY, user.getUserPicture());
        editor.commit();


        user.setUserPicture(null);

        intent.putExtra(USER_EXTRA, user);
        startActivity(intent);
        finish();
    }
}
