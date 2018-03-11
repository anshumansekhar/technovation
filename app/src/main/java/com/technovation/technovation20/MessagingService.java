package com.technovation.technovation20;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anshuman-HP on 17-02-2018.
 */

public class MessagingService extends FirebaseMessagingService {

    Map<String,String> notificationData=new HashMap();
    public  static boolean changed=false;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("Message", "From: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size() > 0){
            Log.e("Message", "Data payload: " + remoteMessage.getData());
            notificationData=remoteMessage.getData();
            changed=true;
            Intent i=new Intent("ChangingDrawable");
            i.putExtra("Changed",true);
            sendBroadcast(i);
        }
        if (remoteMessage.getNotification() != null) {
            Log.e("Message", "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }
        sendNotification();
    }

    private void sendNotification() {
        Intent intent = new Intent(this, NotificationsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_black_24dp)
                        .setContentTitle(notificationData.get("title").toString())
                        .setContentText(notificationData.get("body").toString())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
