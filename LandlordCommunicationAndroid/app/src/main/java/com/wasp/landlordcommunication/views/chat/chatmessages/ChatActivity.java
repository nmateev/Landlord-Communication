package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;

import static com.wasp.landlordcommunication.utils.Constants.CHAT_SESSION_ID_EXTRA;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();

        //TODO get information from chat list and form property management redirect here
        int id = intent.getIntExtra(CHAT_SESSION_ID_EXTRA, 0);
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

    }
}
