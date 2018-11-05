package com.wasp.landlordcommunication.views.chat;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;
import com.wasp.landlordcommunication.views.chat.chatmessages.ChatActivity;

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

        mChatListPresenter.setUserId(getUserId());
        mChatListPresenter.setUserType(getUserType());

        mChatListFragment.setNavigator(this);
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

    @Override
    public void navigateToChat(int chatSessionId) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Constants.CHAT_SESSION_ID_EXTRA, chatSessionId);
        startActivity(intent);
    }
}
