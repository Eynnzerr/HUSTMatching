package com.example.hustmatching.network;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Test1 {
    @FormUrlEncoded
    @POST(Api.SEND_POST)
    Call<ResponseBody> sendPost(@Header("Authorization") String token, @FieldMap() Map<String, String> map);
}
