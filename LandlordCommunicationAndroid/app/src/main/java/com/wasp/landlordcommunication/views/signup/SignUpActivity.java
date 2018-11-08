package com.wasp.landlordcommunication.views.signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.BitmapCacheRepository;
import com.wasp.landlordcommunication.repositories.cacherepostories.LruBitmapCacheRepository;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.home.HomeActivity;
import com.wasp.landlordcommunication.views.signup.secondform.SignUpSecondFormFragment;
import com.wasp.landlordcommunication.views.signup.secondform.SignUpSecondFromContracts;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static com.wasp.landlordcommunication.utils.Constants.USER_EXTRA;

public class SignUpActivity extends DaggerAppCompatActivity implements SignUpContracts.Navigator, SignUpSecondFromContracts.Navigator {

    @Inject
    SignUpFragment mSignUpFragment;

    @Inject
    SignUpSecondFormFragment mSignUpSecondFormFragment;

    @Inject
    SignUpContracts.Presenter mPresenter;

    @Inject
    SignUpSecondFromContracts.Presenter mSecondFormPresenter;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mSignUpFragment.setNavigator(this);
        mSignUpFragment.setPresenter(mPresenter);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_sign_up, mSignUpFragment)
                .commit();

    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        mSecondFormPresenter = null;
        super.onDestroy();
    }


    @Override
    public void navigateToNextRegistrationForm(String userName, String userPassword) {

        mSecondFormPresenter.setRegistrationInformation(userName, userPassword);
        mSignUpSecondFormFragment.setNavigator(this);
        mSignUpSecondFormFragment.setPresenter(mSecondFormPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_sign_up, mSignUpSecondFormFragment)
                .commit();
    }

    @Override
    public void navigateToHomeWithUser(User user) {
        clearSavedPreferences();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(USER_EXTRA, user);
        startActivity(intent);
        finish();
    }

    private void clearSavedPreferences() {
        mEditor = mPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }
}
