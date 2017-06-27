package com.jdkgroup.jkshopping.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import  com.jdkgroup.jkshopping.R;
import  com.jdkgroup.jkshopping.base.BaseAppCompatActivity;
import  com.jdkgroup.jkshopping.general.General;
import  com.jdkgroup.jkshopping.general.GeneralImplement;
import  com.jdkgroup.jkshopping.utils.AppKeyword;
import  com.jdkgroup.jkshopping.utils.GlobalClass;
import  com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ActivityProfile extends BaseAppCompatActivity implements View.OnClickListener {

    private Activity activity;

    private General general;
    private GlobalClass globalClass;
    private SaveSharedPrefernces ssp;

    private String mediaPath;
    private File file;
    private MultipartBody.Part fileToUpload;

    private Toolbar toolBar;

    private CoordinatorLayout coordinatorLayout;
    private AppCompatEditText appedtUsername;
    private CircleImageView circleivProfileImage;
    private AppCompatImageView appivImageUpload;
    public Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        activity = this;

        general = new GeneralImplement();
        ssp = new SaveSharedPrefernces();
        globalClass = GlobalClass.getInstance();

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        toolBar.setTitle("Profile");
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

        appedtUsername = (AppCompatEditText) findViewById(R.id.appedtUsername);
        appivImageUpload = (AppCompatImageView) findViewById(R.id.appivImageUpload);
        circleivProfileImage = (CircleImageView) findViewById(R.id.circleivProfileImage);

        appivImageUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appivImageUpload:
                selectImage();
                break;
        }
    }

    private void galleryIntent() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGallery.setType("image/*");
        startActivityForResult(intentGallery, 0);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            mediaPath = destination.getAbsolutePath();
            circleivProfileImage.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            SocketUploadFile(mediaPath);
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    cameraIntent();

                } else if (items[item].equals("Choose from Gallery")) {
                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        general.HideKeyboard(activity, AppKeyword.keyboardtypehide);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                circleivProfileImage.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if (general.CheckInternetConnection(activity) == true) {
                    showProgressDialog(activity);
                    SocketUploadFile(mediaPath);
                    uploadFile("kamal", "1");
                } else {

                }

            } else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                onCaptureImageResult(data);
            }
        } catch (Exception e) {

        }

    }

    private void uploadFile(final String username, final String userid) {

        if (mediaPath != null) {
            file = new File(mediaPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        } else {
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            fileToUpload = MultipartBody.Part.createFormData("file", "", requestBody);
        }

        RequestBody requestBodyUserName, requestBodyUserId;

        requestBodyUserName = RequestBody.create(MediaType.parse("text/plain"), "username");
        requestBodyUserId = RequestBody.create(MediaType.parse("text/plain"), "1");

        HashMap<String, RequestBody> mapParam = new HashMap<>();
        mapParam.put("username", requestBodyUserName);
        mapParam.put("userid", requestBodyUserId);

        hideProgressDialog();
        //APIUPLOAD(fileToUpload, mapParam);
    }

    private void SocketUploadFile(final String mediaPath)
    {
        new Thread(new Runnable() {
            public void run() {
                Socket sock;
                try {
                    sock = new Socket("192.168.43.174", 3000);
                    System.out.println("Connecting...");

                    File myFile = new File(mediaPath);
                    byte[] byteArray = new byte[(int) myFile.length()];

                    FileInputStream fis = new FileInputStream(myFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    DataInputStream dis = new DataInputStream(bis);
                    dis.readFully(byteArray, 0, byteArray.length);

                    OutputStream os = sock.getOutputStream();

                    //Sending file name and file size to the server
                    DataOutputStream dos = new DataOutputStream(os);
                    dos.writeUTF(myFile.getName());
                    dos.writeLong(byteArray.length);
                    dos.write(byteArray, 0, byteArray.length);
                    dos.flush();

                    //Sending file data to the server
                    os.write(byteArray, 0, byteArray.length);
                    os.flush();


                    sock.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*private void APIUPLOAD(MultipartBody.Part fileToUpload, final Map<String, RequestBody> partMap) {
        RetrofitClient retroClient = new RetrofitClient();
        ApiService apiService = retroClient.getApiService();

        Call<ResponseData> call = apiService.uploaduserprofile(fileToUpload, partMap);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    hideProgressDialog();
                    ResponseData responseData = response.body();

                    if (responseData.getCode() == 1) {
                        mediaPath = "";
                        System.out.print(responseData.getMessage());
                    } else {
                        hideProgressDialog();
                        System.out.print(responseData.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                hideProgressDialog();

            }
        });
    }*/
}
