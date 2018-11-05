package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
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

        //if the user navigates from his chat lists activity
        if (intent.hasExtra(CHAT_SESSION_ID_EXTRA)) {

            mChatPresenter.setChatSessionId(intent.getIntExtra(CHAT_SESSION_ID_EXTRA, 0));

        } else {
            //in the case when the user selects to message another user through the property management chat option
            if ((intent.hasExtra(Constants.LOGGED_IN_USER_ID_EXTRA)) && (intent.hasExtra(Constants.CONTACTED_USER_ID_EXTRA))) {
                mChatPresenter.setFirstChatMember(intent.getIntExtra(Constants.LOGGED_IN_USER_ID_EXTRA, 0));
                mChatPresenter.setSecondChatMember(intent.getIntExtra(Constants.CONTACTED_USER_ID_EXTRA, 0));

            }
        }

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
