package com.jdkgroup.jkshopping.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import  com.jdkgroup.jkshopping.R;
import  com.jdkgroup.jkshopping.base.BaseAppCompatActivity;
import  com.jdkgroup.jkshopping.general.General;
import  com.jdkgroup.jkshopping.general.GeneralImplement;
import  com.jdkgroup.jkshopping.utils.AppKeyword;
import  com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

public class ActivityForgotPassword extends BaseAppCompatActivity implements View.OnClickListener {
    private Activity activity;
    private Toolbar toolBar;

    private General general;
    private SaveSharedPrefernces ssp;

    private AppCompatEditText appedtEmail;
    private TextInputLayout tilEmail;
    private AppCompatButton appbtnForgotPassword;
    private AppCompatTextView apptvTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        activity = this;

        general = new GeneralImplement();
        ssp = new SaveSharedPrefernces();

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        toolBar.setNavigationIcon(R.drawable.ic_back);

        toolBar.setTitleTextColor(Color.WHITE);

        for (int i = 0; i < toolBar.getChildCount(); i++) {
            View view = toolBar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(getApplicationContext().getAssets(), "fonts/sourcesanspro_regular.ttf");
                if (tv.getText().equals(toolBar.getTitle())) {
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }

        apptvTitle = (AppCompatTextView) findViewById(R.id.apptvTitle);
        appedtEmail = (AppCompatEditText) findViewById(R.id.appedtEmail);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        appbtnForgotPassword = (AppCompatButton) findViewById(R.id.appbtnForgotPassword);

        general.FontAppCompatTextView(apptvTitle, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtEmail, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatButton(appbtnForgotPassword, AppKeyword.sourcesanspro_regular);

        appbtnForgotPassword.setOnClickListener(this);

        toolBar.setNavigationOnClickListener(v -> onBackPressed());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onSuccess() {
        hideProgressDialog();
        general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGIN_2);
        finish();
    }

    private boolean Validation(String email) {
        boolean valid = true;

        if (general.ValidationBlank(email) == true) {
            tilEmail.setError("enter email");
            valid = false;
        } else {
            tilEmail.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appbtnForgotPassword:
                String email;

                email = appedtEmail.getText().toString();

                boolean validation = Validation(email);
                if (validation == true) {
                    showProgressDialog(activity);

                    onSuccess();
                }
                break;
        }
    }
}