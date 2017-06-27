package com.jdkgroup.jkshopping.presenter;

/**
 * Created by kamlesh on 6/11/2017.
 */

import android.content.Context;

import  com.jdkgroup.jkshopping.base.BaseAppCompatActivity;
import  com.jdkgroup.jkshopping.model.ResponseData;
import  com.jdkgroup.jkshopping.retrofit.ApiService;
import  com.jdkgroup.jkshopping.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BaseAppCompatActivity{

    private final Context context;
    private final LoginPresenterListener mListener;

    public interface LoginPresenterListener{
        void LoginReady(ResponseData login);
    }

    public LoginPresenter(LoginPresenterListener listener, Context context){
        this.mListener = listener;
        this.context = context;
    }

    public void getLogin(){
        RetrofitClient retroClient = new RetrofitClient();
        ApiService apiService = retroClient.getApiService();

        Call<ResponseData> call = apiService.section_category_messagecollection();

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    hideProgressDialog();

                    ResponseData result = response.body();
                    if(result != null)
                        mListener.LoginReady(result);
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable response) {
                hideProgressDialog();
                call.cancel();
                //general.DisplaySnackbar(coordinatorLayout, response.getMessage(), null, null, 0, 1, AppKeyword.STATUS);
            }
        });
    }
}

