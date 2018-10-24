package com.wasp.landlordcommunication.views.chat;

import android.os.Bundle;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.BaseDrawerActivity;

import butterknife.ButterKnife;

public class ChatActivity extends BaseDrawerActivity {

    public static final long DRAWER_IDENTIFIER = 652;
    private String mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
    }

    @Override
    protected long getIdentifier() {
        return DRAWER_IDENTIFIER;
    }

    @Override
    protected String getUserType() {
        return mUserType;
    }

    @Override
    protected String getUserDrawerName() {
        return null;
    }
}
