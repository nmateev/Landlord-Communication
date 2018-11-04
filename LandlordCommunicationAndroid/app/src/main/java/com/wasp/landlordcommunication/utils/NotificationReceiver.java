package com.wasp.landlordcommunication.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.views.login.LoginActivity;

public class NotificationReceiver extends BroadcastReceiver {

    private NotificationManagerCompat mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(Constants.PURPOSE_EXTRA).equals(Constants.NOTIFICATION_CHANNEL_NAME)) {

            mNotificationManager = NotificationManagerCompat.from(context);

            int notificationCode = intent.getIntExtra(Constants.NOTIFICATION_CODE_EXTRA, 0);
            String notificationTitle = intent.getStringExtra(Constants.NOTIFICATION_TITLE_EXTRA);
            String notificationDescription = intent.getStringExtra(Constants.NOTIFICATION_DESCRIPTION_EXTRA);
            String notificationRentAddress = intent.getStringExtra(Constants.NOTIFICATION_DESCRIPTION_ADDRESS_EXTRA);

            mBuilder = new NotificationCompat.Builder(context, Constants.RENT_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notificationDescription)
                    .setContentText(notificationRentAddress)
                    .setSubText(notificationTitle)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setColorized(true)
                    .setColor(Color.parseColor(Constants.RENT_NOTIFICATION_COLOUR_STRING))
                    .setAutoCancel(true);


            mNotificationManager.notify(notificationCode, mBuilder.build());
        }
    }
}
