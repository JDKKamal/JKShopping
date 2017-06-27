package com.jdkgroup.jkshopping.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveSharedPrefernces {

    SharedPreferences sharedPreferences;

    public static final String PREFS_NAME = "MyPref";
    public static final String KEY_PREFS = "key_pref";

    public static final String KEY_USERID = "key_userid";
    public static final String KEY_Username = "key_username";
    public static final String KEY_Email = "key_email";
    public static final String KEY_MobileNo = "key_mobile_no";

    public static final String KEY_Firstname = "key_firstname";
    public static final String KEY_Lastname = "key_lastname";
    public static final String KEY_Address = "key_address";
    public static final String KEY_Profile_Picture = "key_profile_picture";

    public static final String KEY_Firebase_Notification_Id = "key_firebase_notification_id";

    public void setFirebaseNotificationId(Context context, String userid) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Firebase_Notification_Id, userid);
        editor.commit();
    }

    public String getFirebaseNotificationId(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String firebasenotificationid = sharedPreferences.getString(KEY_Firebase_Notification_Id, "");

        return firebasenotificationid;
    }


    public void setUserId(Context context, String userid) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, userid);
        editor.commit();
    }

    public String getUserId(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString(KEY_USERID, "");

        return userid;
    }

    public void setUsername(Context context, String username) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Username, username);
        editor.commit();
    }

    public String getUsername(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_Username, "");

        return username;
    }

    public void setEmail(Context context, String email) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Email, email);
        editor.commit();
    }

    public String getEmail(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_Email, "");

        return email;
    }

    public void setMobileNo(Context context, String mobileno) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MobileNo, mobileno);
        editor.commit();
    }

    public String getMobileNo(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String mobileno = sharedPreferences.getString(KEY_MobileNo, "");

        return mobileno;
    }

    public void setFirstname(Context context, String firstname) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Firstname, firstname);
        editor.commit();
    }

    public String getFirstname(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String firstname = sharedPreferences.getString(KEY_Firstname, "");

        return firstname;
    }

    public void setLastname(Context context, String lastname) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Lastname, lastname);
        editor.commit();
    }

    public String getLastname(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String lastname = sharedPreferences.getString(KEY_Lastname, "");

        return lastname;
    }


    public void setAddress(Context context, String address) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Address, address);
        editor.commit();
    }

    public String getAddress(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String address = sharedPreferences.getString(KEY_Address, "");

        return address;
    }

    public void setProfilePicture(Context context, String profilepicture) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Profile_Picture, profilepicture);
        editor.commit();
    }

    public String getProfilePicture(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String profilepicture = sharedPreferences.getString(KEY_Profile_Picture, "");

        return profilepicture;
    }

}