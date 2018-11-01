package com.wasp.landlordcommunication.views.propertymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;

public class PropertyManagementActivity extends AppCompatActivity {

    private int mPropertyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_management);

        Intent incomingIntent = getIntent();
        mPropertyId = incomingIntent.getIntExtra(Constants.PROPERTY_ID_EXTRA, 0);


    }
}
