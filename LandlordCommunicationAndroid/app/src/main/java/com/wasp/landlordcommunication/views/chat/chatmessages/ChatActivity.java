package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.CHAT_SESSION_ID_EXTRA;

public class ChatActivity extends BaseDrawerActivity {
    public static final long DRAWER_IDENTIFIER = -1;

    @Inject
    ChatFragment mChatFragment;

    @Inject
    ChatContracts.Presenter mChatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        //TODO get information from chat list and form property management redirect here
        int id = intent.getIntExtra(CHAT_SESSION_ID_EXTRA, 0);
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

        mChatPresenter.setUserId(getUserId());
        mChatPresenter.setUserType(getUserType());


        mChatFragment.setPresenter(mChatPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_chat, mChatFragment)
                .commit();

    }

    @Override
    protected long getIdentifier() {
        //should not return valid identifier
        return DRAWER_IDENTIFIER;
    }
}
