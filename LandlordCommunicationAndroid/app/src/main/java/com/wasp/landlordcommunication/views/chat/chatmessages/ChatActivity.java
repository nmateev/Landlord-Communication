package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.content.Intent;
import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;
import com.wasp.landlordcommunication.views.chat.image.ImageViewActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.wasp.landlordcommunication.utils.Constants.CHAT_SESSION_ID_EXTRA;
import static com.wasp.landlordcommunication.utils.Constants.CHAT_SESSION_LANDLORD_ID_EXTRA;
import static com.wasp.landlordcommunication.utils.Constants.CHAT_SESSION_TENANT_ID_EXTRA;

public class ChatActivity extends BaseDrawerActivity implements ChatContracts.Navigator {
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
            mChatPresenter.setChatSessionTenantId(intent.getIntExtra(CHAT_SESSION_TENANT_ID_EXTRA, 0));
            mChatPresenter.setChatSessionLandlordId(intent.getIntExtra(CHAT_SESSION_LANDLORD_ID_EXTRA, 0));

        } else {
            //in the case when the user selects to message another user through the property management chat option
            if ((intent.hasExtra(Constants.LOGGED_IN_USER_ID_EXTRA)) && (intent.hasExtra(Constants.CONTACTED_USER_ID_EXTRA))) {
                mChatPresenter.setFirstChatMember(intent.getIntExtra(Constants.LOGGED_IN_USER_ID_EXTRA, 0));
                mChatPresenter.setSecondChatMember(intent.getIntExtra(Constants.CONTACTED_USER_ID_EXTRA, 0));

            }
        }

        mChatPresenter.setUserId(getUserId());
        mChatPresenter.setUserType(getUserType());

        mChatFragment.setNavigator(this);
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

    @Override
    public void navigateToImageView(String imageMessage) {

        Intent intent = new Intent(this, ImageViewActivity.class);
        intent.putExtra(Constants.IMAGE_MESSAGE_EXTRA, imageMessage);
        startActivity(intent);
    }
}
