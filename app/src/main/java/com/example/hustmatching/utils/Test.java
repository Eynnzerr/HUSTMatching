package com.example.hustmatching.utils;

import com.example.hustmatching.network.Api;
import com.example.hustmatching.response.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Test {

    @FormUrlEncoded
    @POST(Api.SEND_POST)
    Call<Response> sendNetPost(@FieldMap() Map<String,String> map);
}
