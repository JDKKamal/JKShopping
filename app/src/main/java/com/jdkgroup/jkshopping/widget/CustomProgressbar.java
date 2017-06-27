package com.jdkgroup.jkshopping.widget;

import android.app.Dialog;
import android.content.Context;

import  com.jdkgroup.jkshopping.R;

public class CustomProgressbar {
    Context context;
    Dialog dialog;

    public CustomProgressbar(Context context) {
        this.context = context;
        dialog = new Dialog(context, R.style.CircularProgressTransparent);
    }

    public void show(Boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setContentView(R.layout.custom_progressbar);
        dialog.show();
    }

    public Boolean isShowing() {
        return dialog.isShowing();
    }

    public void hide() {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
        }
    }
}
