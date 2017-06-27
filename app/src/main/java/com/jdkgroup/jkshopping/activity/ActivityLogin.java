package com.jdkgroup.jkshopping.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jdkgroup.jkshopping.R;
import com.jdkgroup.jkshopping.base.BaseAppCompatActivity;
import com.jdkgroup.jkshopping.general.General;
import com.jdkgroup.jkshopping.general.GeneralImplement;
import com.jdkgroup.jkshopping.model.ResponseData;
import com.jdkgroup.jkshopping.model.param.RequestData;
import com.jdkgroup.jkshopping.model.param.login_103.RequestLogin;
import com.jdkgroup.jkshopping.model.signup_100.ResponseJkLogin_103;
import com.jdkgroup.jkshopping.model.signup_100.ResponseSignUp;
import com.jdkgroup.jkshopping.presenter.LoginPresenter;
import com.jdkgroup.jkshopping.utils.AppKeyword;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ActivityLogin extends BaseAppCompatActivity implements View.OnClickListener {
    private Activity activity;
    private Toolbar toolBar;

    private General general;
    private SaveSharedPrefernces saveSharedPrefernces;
    private Gson gson;
    private String paramvalue;

    private CoordinatorLayout coordinatorLayout;
    private AppCompatEditText appedtEmail, appedtPassword;
    private AppCompatButton appbtnLogin;
    private AppCompatTextView apptvTitle, apptvLinkSignUp, apptvLinkForgotPassword;
    private TextInputLayout tilEmail, tilPassword;

    private LoginPresenter loginPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity = this;

        general = new GeneralImplement();
        saveSharedPrefernces = new SaveSharedPrefernces();
        // loginPresenter = new LoginPresenter(this, activity);

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        toolBar.setTitleTextColor(Color.WHITE);

        for (int i = 0; i < toolBar.getChildCount(); i++) {
            View view = toolBar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sourcesanspro_regular.ttf");
                if (tv.getText().equals(toolBar.getTitle())) {
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        appedtEmail = (AppCompatEditText) findViewById(R.id.appedtEmail);
        appedtPassword = (AppCompatEditText) findViewById(R.id.appedtPassword);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        apptvTitle = (AppCompatTextView) findViewById(R.id.apptvTitle);
        apptvLinkSignUp = (AppCompatTextView) findViewById(R.id.apptvLinkSignUp);
        apptvLinkForgotPassword = (AppCompatTextView) findViewById(R.id.apptvLinkForgotPassword);
        appbtnLogin = (AppCompatButton) findViewById(R.id.appbtnLogin);

        general.FontAppCompatEditText(appedtEmail, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtPassword, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatTextView(apptvTitle, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatTextView(apptvLinkForgotPassword, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatTextView(apptvLinkSignUp, AppKeyword.sourcesanspro_bold);
        general.FontAppCompatButton(appbtnLogin, AppKeyword.sourcesanspro_regular);

        appbtnLogin.setOnClickListener(this);
        apptvLinkSignUp.setOnClickListener(this);
        apptvLinkForgotPassword.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGOUT_0);
    }

    public void onSuccess() {
        general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_DRAWER_1);
        finish();
    }

    private boolean Validation(String email, String password) {
        boolean valid = true;

        if (general.ValidationBlank(email) == true) {
            tilEmail.setError("enter email");
            valid = false;
        } else {
            tilEmail.setError(null);
        }

        if (general.ValidationBlank(password) == true) {
            tilPassword.setError("enter password");
            valid = false;
        } else {
            tilPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appbtnLogin:
                String email, password;

                email = appedtEmail.getText().toString();
                password = appedtPassword.getText().toString();

                boolean validation = Validation(email, password);
                if (validation == true) {

                    gson = new Gson();
                    RequestData requestData = new RequestData(AppKeyword.CODE_SIGNUP_AUTHENTICATION);
                    RequestLogin requestLogin = new RequestLogin(requestData, email, password, "");
                    paramvalue = gson.toJson(requestLogin);

                    new AsyncTaskLogin().execute();
                } else {

                }
                break;

            case R.id.apptvLinkSignUp:
                general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_SIGN_UP_21);
                break;

            case R.id.apptvLinkForgotPassword:
                general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_FORGOT_PASSWORD_22);
                break;
        }
    }

    public class AsyncTaskLogin extends AsyncTask<Void, Void, Void> {

        private String responsevalue;
        private Socket socket;
        private OutputStream outputStream;
        private DataOutputStream dataOutputStream;
        private InputStream inputStream;
        private DataInputStream dataInputStream;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(activity);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                InetAddress inetAddress = InetAddress.getByName(AppKeyword.HOSTNAME);
                socket = new Socket(inetAddress, AppKeyword.PORT);
                outputStream = socket.getOutputStream();

                //TODO SENT THE CLIENT REQUEST TO SERVER
                dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(AppKeyword.CODE_SIGNUP_AUTHENTICATION);
                dataOutputStream.writeUTF(paramvalue);

                //TODO SERVER RESPONSE
                inputStream = socket.getInputStream();
                dataInputStream = new DataInputStream(inputStream);

                responsevalue = dataInputStream.readUTF();
            } catch (IOException ex) {

            } finally {
                //TODO socket CLOSE MUST BE
                try {
                    outputStream.flush();
                    dataOutputStream.flush();

                    outputStream.close();
                    dataOutputStream.close();
                    inputStream.close();
                    dataInputStream.close();

                    socket.close();

                } catch (IOException e) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            paramvalue = null;

            hideProgressDialog();

            ResponseJkLogin_103 responseJkLogin103 = gson.fromJson(responsevalue, ResponseJkLogin_103.class);

            ResponseData responseData = responseJkLogin103.getReponsedata();
            if (responseData.getCode() == AppKeyword.WS_CODE_0) {
                general.DisplaySnackbar(coordinatorLayout, responseData.getMessage(), null, null, 0, 1, AppKeyword.STATUS);
            } else {
                appedtEmail.setText(null);
                appedtPassword.setText(null);

                ResponseSignUp responseSignUp = responseJkLogin103.getResponsesignup();
                saveSharedPrefernces.setUserId(activity, responseSignUp.getUnique_id());
                saveSharedPrefernces.setUsername(activity, responseSignUp.getUsername());
                saveSharedPrefernces.setEmail(activity, responseSignUp.getEmail());
                saveSharedPrefernces.setMobileNo(activity, responseSignUp.getCell_no());

                general.DisplaySnackbar(coordinatorLayout, responseData.getMessage(), null, null, 0, 1, AppKeyword.STATUS);
                onSuccess();
            }

        }
    }

  /*  @Override
    public void LoginReady(ResponseData login) {
       System.out.println(login.getCode());
    }*/
}