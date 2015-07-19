package com.hoodbluck.authum.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.hoodbluck.authum.R;
import com.hoodbluck.authum.activities.ConfirmAuthumPatterActivity_;
import com.hoodbluck.authum.models.GcmData;

import org.androidannotations.annotations.EService;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EService
public class GcmIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 0x2345;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        if (extras != null) {
            GcmData data = new Gson().fromJson(extras.getString("data"), GcmData.class);

            sendNotification(data);
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(GcmData data) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = ConfirmAuthumPatterActivity_.intent(this)
                .mClientId(data.getClientId())
                .get();


        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_signin_btn_icon_light)
                        .setContentTitle("Authum")
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(data.getMessage()))
                        .setContentText(data.getMessage());

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
