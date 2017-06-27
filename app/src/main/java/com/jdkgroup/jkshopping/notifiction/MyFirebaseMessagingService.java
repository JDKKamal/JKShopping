package com.jdkgroup.jkshopping.notifiction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jdkgroup.jkshopping.R;
import com.jdkgroup.jkshopping.activity.ActivityLogin;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    String title = "", icon = "", userid, username;
    Bitmap bitmap;

    private Context context;
    private String device_info;

    private SaveSharedPrefernces ssp;


    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        ssp = new SaveSharedPrefernces();

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        title = remoteMessage.getNotification().getTitle();
        //icon = remoteMessage.getNotification().getIcon();
        //bitmap = getBitmapfromUrl(icon);

        userid = remoteMessage.getData().get("userid");
        username = String.valueOf(remoteMessage.getData().get("username"));

        sendNotification(String.valueOf(Html.fromHtml(remoteMessage.getNotification().getBody())));
    }

    private void sendNotification(String messageBody) {
        Intent intent = null;

        intent = new Intent(this, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(bitmap)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());


    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}
