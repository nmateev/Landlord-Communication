package com.wasp.landlordcommunication.views.chat;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ChatListActivity extends BaseDrawerActivity implements ChatListContracts.Navigator {

    public static final long DRAWER_IDENTIFIER = 652;

    @Inject
    ChatListContracts.Presenter mChatListPresenter;

    @Inject
    ChatListFragment mChatListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        ButterKnife.bind(this);

      /*  //TODO Move to specific chat session activity
        Intent incomingIntent = getIntent();
        int loggedInUser = incomingIntent.getIntExtra(Constants.LOGGED_IN_USER_ID_EXTRA, getUserId());
        int contactedUserId = incomingIntent.getIntExtra(Constants.CONTACTED_USER_ID_EXTRA, 0);
        //TODO Move to specific chat session activity*/

        mChatListPresenter.setUserId(getUserId());
        mChatListPresenter.setUserType(getUserType());

        mChatListFragment.setPresenter(mChatListPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_chat_list, mChatListFragment)
                .commit();


    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}
