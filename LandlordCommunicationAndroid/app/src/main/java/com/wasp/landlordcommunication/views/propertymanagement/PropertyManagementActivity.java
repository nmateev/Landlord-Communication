package com.wasp.landlordcommunication.views.propertymanagement;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;
import com.wasp.landlordcommunication.views.chat.ChatListActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PropertyManagementActivity extends BaseDrawerActivity implements PropertyManagementContracts.Navigator {
    private static final int DRAWER_IDENTIFIER = -1;

    @Inject
    PropertyManagementFragment mPropertyManagementFragment;

    @Inject
    PropertyManagementContracts.Presenter mPresenter;

    private int mPropertyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_management);
        ButterKnife.bind(this);

        Intent incomingIntent = getIntent();
        mPropertyId = incomingIntent.getIntExtra(Constants.PROPERTY_ID_EXTRA, 0);
        mPresenter.setSelectedPropertyId(mPropertyId);

        mPresenter.setUserId(getUserId());
        mPresenter.setUserType(getUserType());

        mPropertyManagementFragment.setNavigator(this);
        mPropertyManagementFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_property_management, mPropertyManagementFragment)
                .commit();


    }

    @Override
    protected long getIdentifier() {
        //should not return valid identifier
        return DRAWER_IDENTIFIER;
    }

    @Override
    public void navigateToChatWithUsers(int userId, int otherUserId) {

        //TODO Navigate to specific chat session
        Intent intent = new Intent(this, ChatListActivity.class);

        intent.putExtra(Constants.LOGGED_IN_USER_ID_EXTRA, userId);
        intent.putExtra(Constants.CONTACTED_USER_ID_EXTRA, otherUserId);
        startActivity(intent);
    }
}
