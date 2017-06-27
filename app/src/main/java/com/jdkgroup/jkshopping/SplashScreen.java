package com.jdkgroup.jkshopping;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import  com.jdkgroup.jkshopping.R;
import com.greysonparrelli.permiso.Permiso;
import  com.jdkgroup.jkshopping.general.General;
import  com.jdkgroup.jkshopping.general.GeneralImplement;
import  com.jdkgroup.jkshopping.utils.AppKeyword;
import  com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

public class SplashScreen extends AppCompatActivity {

    private Activity activity;
    private Thread mSplashThread;
    private General general;
    private SaveSharedPrefernces saveSharedPrefernces;
    private AppCompatTextView actAppTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        activity = this;

        Permiso.getInstance().setActivity(this);

        saveSharedPrefernces = new SaveSharedPrefernces();
        general = new GeneralImplement();

        general.ConsoleSystem(SplashScreen.class.getName(), "FCM TOKEN " + saveSharedPrefernces.getFirebaseNotificationId(activity), AppKeyword.STATUS);

        actAppTitle = (AppCompatTextView) findViewById(R.id.actAppTitle);

        general.FontAppCompatTextView(actAppTitle, AppKeyword.sourcesanspro_bold);
        checkPermissions();
    }

    private void setSplashScreen() {
        if (!saveSharedPrefernces.getUserId(activity).isEmpty()) {
            AppKeyword.APPMODE = AppKeyword.APPMODE_LOGIN;

            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_DRAWER_1);
            finish();
        }

        // The thread to wait for splash screen events
        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        if (saveSharedPrefernces.getUserId(activity).isEmpty()) {
                            wait(3000L);
                        }
                    }
                } catch (InterruptedException ex) {

                }

                if (saveSharedPrefernces.getUserId(activity).isEmpty()) {
                    general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGIN_2);
                    finish();
                }
            }
        };

        mSplashThread.start();
    }

    // Processes splash screen touch events
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mSplashThread.notifyAll();
            }
        }
        return true;
    }

    private void checkPermissions() {
        Permiso.getInstance().requestPermissions(new Permiso.IOnPermissionResult() {
                                                     @Override
                                                     public void onPermissionResult(Permiso.ResultSet resultSet) {
                                                         if (!resultSet.isPermissionGranted(Manifest.permission.INTERNET)
                                                                 || !resultSet.isPermissionGranted(Manifest.permission.ACCESS_NETWORK_STATE)
                                                                 || !resultSet.isPermissionGranted(Manifest.permission.ACCESS_WIFI_STATE)
                                                                 || !resultSet.isPermissionGranted(Manifest.permission.CHANGE_WIFI_STATE)
                                                                 || !resultSet.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                                 || !resultSet.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
                                                                 || !resultSet.isPermissionGranted(Manifest.permission.CAMERA)) {
                                                             new AlertDialog.Builder(SplashScreen.this).setTitle(R.string.permission_permissions_needed)
                                                                     .setMessage(getResources().getString(R.string.permission_go_to_permission_and_grant_them))
                                                                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                         @Override
                                                                         public void onClick(DialogInterface dialog, int which) {
                                                                             Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                                             Uri uri = Uri.fromParts("package", getPackageName(), null);
                                                                             intent.setData(uri);
                                                                             startActivityForResult(intent, 1001);
                                                                         }
                                                                     }).setCancelable(false).show();
                                                         } else {
                                                             setSplashScreen();
                                                         }
                                                     }

                                                     @Override
                                                     public void onRationaleRequested(Permiso.IOnRationaleProvided callback, String... permissions) {
                                                         Permiso.getInstance().showRationaleInDialog("Permissions needed", "App needs some of the permissions, Please allow it.", null, callback);
                                                     }
                                                 },
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Permiso.getInstance().setActivity(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Permiso.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults);
    }
}
