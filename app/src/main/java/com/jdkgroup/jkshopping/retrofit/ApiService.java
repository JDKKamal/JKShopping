package com.jdkgroup.jkshopping.retrofit;

import com.jdkgroup.jkshopping.model.ResponseData;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


public interface ApiService {

    @FormUrlEncoded
    @POST("webservice_goodmorning")
    Call<ResponseData> section_category_messagecollection();

    @Multipart
    @POST("uploadmessagecollection")
    Call<ResponseData> uploadmessagecollection(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);
}
