package com.jdkgroup.jkshopping.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import  com.jdkgroup.jkshopping.widget.CustomProgressbar;

public class BaseAppCompatActivity extends AppCompatActivity {

    private CustomProgressbar customProgressbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgressDialog(Context context) {
        customProgressbar = new CustomProgressbar(context);
        if (customProgressbar != null && customProgressbar.isShowing()) {
            customProgressbar.hide();
        }
        customProgressbar.show(false);
        customProgressbar.isShowing();
    }

    public  void hideProgressDialog() {
        if (customProgressbar != null && customProgressbar.isShowing()) {
            customProgressbar.hide();
        }
        customProgressbar = null;
    }

}
