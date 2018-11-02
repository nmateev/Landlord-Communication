package com.wasp.landlordcommunication.views.chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import butterknife.ButterKnife;

public class ChatActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 652;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        //TODO Move to specific chat session activity
        Intent incomingIntent = getIntent();
        int loggedInUser = incomingIntent.getIntExtra(Constants.LOGGED_IN_USER_ID_EXTRA, getUserId());
        int contactedUserId = incomingIntent.getIntExtra(Constants.CONTACTED_USER_ID_EXTRA, 0);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

}
