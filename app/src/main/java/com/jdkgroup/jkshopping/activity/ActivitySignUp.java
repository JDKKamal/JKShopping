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
import com.jdkgroup.jkshopping.model.param.signup_100.ModelSignUp;
import com.jdkgroup.jkshopping.model.param.signup_100.RequestSignUpInsert;
import com.jdkgroup.jkshopping.utils.AppKeyword;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ActivitySignUp extends BaseAppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private Toolbar toolBar;

    private General general;
    private SaveSharedPrefernces saveSharedPrefernces;
    private Gson gson;
    private String paramvalue;

    private CoordinatorLayout coordinatorLayout;
    private AppCompatEditText appedtUsername, appedtEmail, appedtPassword, appedtConfirmPassword, appedtMobileNo;
    private TextInputLayout tilUsername, tilEmail, tnlPassword, tnlConfirmPassword, tnlMobileNo;
    private AppCompatButton appbtnSubmit;
    private AppCompatTextView apptvAppTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        activity = this;

        general = new GeneralImplement();
        saveSharedPrefernces = new SaveSharedPrefernces();

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

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        apptvAppTitle = (AppCompatTextView) findViewById(R.id.apptvAppTitle);
        appedtUsername = (AppCompatEditText) findViewById(R.id.appedtUsername);
        appedtEmail = (AppCompatEditText) findViewById(R.id.appedtEmail);
        appedtPassword = (AppCompatEditText) findViewById(R.id.appedtPassword);
        appedtConfirmPassword = (AppCompatEditText) findViewById(R.id.appedtConfirmPassword);
        appedtMobileNo = (AppCompatEditText) findViewById(R.id.appedtMobileNo);
        tilUsername = (TextInputLayout) findViewById(R.id.tilUsername);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tnlPassword = (TextInputLayout) findViewById(R.id.tnlPassword);
        tnlConfirmPassword = (TextInputLayout) findViewById(R.id.tnlConfirmPassword);
        tnlMobileNo = (TextInputLayout) findViewById(R.id.tnlMobileNo);
        appbtnSubmit = (AppCompatButton) findViewById(R.id.appbtnSubmit);

        general.FontAppCompatTextView(apptvAppTitle, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtUsername, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtEmail, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtPassword, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtConfirmPassword, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatEditText(appedtMobileNo, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatButton(appbtnSubmit, AppKeyword.sourcesanspro_regular);

        appbtnSubmit.setOnClickListener(this);

        toolBar.setNavigationOnClickListener(v -> onBackPressed());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appbtnSubmit:
                String username, email, password, confirmpassword, mobileno;

                username = appedtUsername.getText().toString();
                email = appedtEmail.getText().toString();
                password = appedtPassword.getText().toString();
                confirmpassword = appedtConfirmPassword.getText().toString();
                mobileno = appedtMobileNo.getText().toString();

                boolean validation = Validation(username, email, password, confirmpassword, mobileno);
                if (validation == true) {

                    gson = new Gson();
                    RequestData requestData = new RequestData(AppKeyword.CODE_SIGNUP_INSERT);

                    List<ModelSignUp> alSignUp = new ArrayList<>();
                    alSignUp.add(new ModelSignUp("", username, email, password, mobileno, "Android"));

                    RequestSignUpInsert requestSignUpInsert = new RequestSignUpInsert(requestData, alSignUp);
                    paramvalue = gson.toJson(requestSignUpInsert);

                    new AsyncTaskSignUpInsert().execute();
                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void onSuccess() {
        hideProgressDialog();
        general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_DRAWER_1);
        finish();
    }

    public class AsyncTaskSignUpInsert extends AsyncTask<Void, Void, Void> {

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
                dataOutputStream.writeInt(AppKeyword.CODE_SIGNUP_INSERT);
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
            hideProgressDialog();

            ResponseData responseData = gson.fromJson(responsevalue, ResponseData.class);

            if (responseData.getCode() == AppKeyword.WS_CODE_0) {
                general.DisplaySnackbar(coordinatorLayout, responseData.getMessage(), null, null, 0, 1, AppKeyword.STATUS);
            } else {
                general.DisplaySnackbar(coordinatorLayout, responseData.getMessage(), null, null, 0, 1, AppKeyword.STATUS);
                onSuccess();
            }

        }
    }

    private boolean Validation(String username, String email, String password, String confirmpassword, String mobileno) {
        boolean valid = true, validpassowrd = false, validconfirmpassword = false;

        if (general.ValidationBlank(username) == true) {
            tilUsername.setError("enter username");
            valid = false;
        } else {
            tilUsername.setError(null);
        }

        if (general.ValidationBlank(email) == true) {
            tilEmail.setError("enter email");
            valid = false;
        } else {
            tilEmail.setError(null);
        }

        if (general.ValidationBlank(password) == true) {
            tnlPassword.setError("enter password");
            valid = false;
        } else {
            tnlPassword.setError(null);
        }

        if (general.ValidationBlank(confirmpassword) == true) {
            tnlConfirmPassword.setError("enter confirm password");
            valid = false;
        } else {
            tnlConfirmPassword.setError(null);
        }

        if (general.ValidationBlank(mobileno) == true) {
            tnlMobileNo.setError("enter mobile no.");
            valid = false;
        } else {
            tnlMobileNo.setError(null);
        }

        //USERNAME
        if (!general.ValidationBlank(username) == true) {
            if (username.length() < 3) {
                tilUsername.setError("enter valid username");
                valid = false;
            } else {
                tilUsername.setError(null);
            }
        }

        //EMAIL
        if (!general.ValidationBlank(email) == true) {
            if (general.ValidationEmail(email) == false) {
                tilEmail.setError("email invalid");
                valid = false;
            } else {
                tilEmail.setError(null);
            }
        }

        //PASSWORD
        if (!general.ValidationBlank(password) == true) {
            if (password.length() < 6) {
                tnlPassword.setError("password contain 6 characters");
                valid = false;
                validpassowrd = false;
            } else {
                tnlPassword.setError(null);
                validpassowrd = true;
            }
        }

        //CONFIRM PASSWORD
        if (!general.ValidationBlank(confirmpassword) == true) {
            if (confirmpassword.length() < 6) {
                tnlConfirmPassword.setError("confirm  password contain 6 characters");
                valid = false;
                validconfirmpassword = false;
            } else {
                tnlConfirmPassword.setError(null);
                validconfirmpassword = true;
            }
        }

        if (validpassowrd == true && validconfirmpassword == true) {

            if (general.Validation2StringEqual(password, confirmpassword) == false) {
                tnlPassword.setError("password not match");
                tnlConfirmPassword.setError("confirm password not match");
                valid = false;
            } else {
                tnlPassword.setError(null);
                tnlConfirmPassword.setError(null);
            }
        }


        //MOBILE
        if (!general.ValidationBlank(mobileno) == true) {
            if (general.ValidationExpression(AppKeyword.REGULAR_EXPRESSION_CELLNO, mobileno) == false) {
                tnlMobileNo.setError("mobile no. invalid");
                valid = false;
            } else {
                tnlMobileNo.setError(null);
            }
        }

        return valid;
    }
}