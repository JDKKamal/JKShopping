package com.jdkgroup.jkshopping.notifiction;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.jdkgroup.jkshopping.general.General;
import com.jdkgroup.jkshopping.general.GeneralImplement;
import com.jdkgroup.jkshopping.utils.AppKeyword;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private SaveSharedPrefernces saveSharedPrefernces;
    private General general;

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        saveSharedPrefernces = new SaveSharedPrefernces();
        general = new GeneralImplement();

        general.ConsoleSystem(MyFirebaseInstanceIDService.class.getName(), "FCM  REFRESH TOKEN " + refreshedToken, AppKeyword.STATUS);

        if (refreshedToken != null && saveSharedPrefernces.getFirebaseNotificationId(getApplicationContext()) != refreshedToken) {
            saveSharedPrefernces.setFirebaseNotificationId(getApplicationContext(), refreshedToken);
        }

        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }

}
