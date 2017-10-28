package com.example.nick.insulinpump;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Nick on 28-Oct-17.
 */

public class InsulinNotificationManager {

    public InsulinNotificationManager(Context context, String notifTitle, String notifText) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(notifTitle).setContentText(notifText);
        int mNotificationId = 001;
        android.app.NotificationManager mNotifyMgr = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
