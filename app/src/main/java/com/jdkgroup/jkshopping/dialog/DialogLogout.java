package com.jdkgroup.jkshopping.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.Window;

import  com.jdkgroup.jkshopping.R;
import  com.jdkgroup.jkshopping.general.General;
import  com.jdkgroup.jkshopping.general.GeneralImplement;
import  com.jdkgroup.jkshopping.utils.AppKeyword;
import  com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

public class DialogLogout extends Dialog implements View.OnClickListener {

    private Activity activity;
    private Dialog dialog;
    private AppCompatTextView actAppName;
    private AppCompatButton acbCancel, acbLogout;

    private General general;
    private SaveSharedPrefernces ssp;

    public DialogLogout(Activity activity) {
        super(activity);
        this.activity = activity;

        general = new GeneralImplement();
        ssp = new SaveSharedPrefernces();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_logout);

        actAppName = (AppCompatTextView) findViewById(R.id.actAppName);
        acbCancel = (AppCompatButton) findViewById(R.id.acbCancel);
        acbLogout = (AppCompatButton) findViewById(R.id.acbLogout);

        acbCancel.setOnClickListener(this);
        acbLogout.setOnClickListener(this);

        general.FontAppCompatTextView(actAppName, AppKeyword.sourcesanspro_bold);
        general.FontAppCompatButton(acbCancel, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatButton(acbLogout, AppKeyword.sourcesanspro_regular);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acbLogout:

                ssp.setUsername(activity, "");
                ssp.setUserId(activity, "");
                ssp.setEmail(activity, "");
                ssp.setMobileNo(activity, "");

                ssp.setFirstname(activity, "");
                ssp.setLastname(activity, "");
                ssp.setAddress(activity, "");
                ssp.setProfilePicture(activity, "");

                activity.finish();

                break;
            case R.id.acbCancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
