package com.example.msapproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotifyMe {

        private static final int NOTIFICATION_ID = 0;
        private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
        private NotificationManager mNotifyManager;
        Context context;

        NotifyMe(Context context) { this.context = context; }

        public void createNotificationChannel(String text) {
            mNotifyManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.WHITE);
            mNotifyManager.createNotificationChannel(notificationChannel);
            createNotification(text);
        }

        public void createNotification(String text) {
            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent notificationPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                    .setContentTitle("Today's Covid-19 data")
                    .setSmallIcon(R.drawable.ic_baseline_local_hospital_24)
                    .setAutoCancel(true)
                    .setContentIntent(notificationPendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);
            mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        }
}
