package com.example.hustmatching.retrofit

import com.example.hustmatching.bean.Reception
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {
    @FormUrlEncoded
    @POST(Api.REGISTER_URL)
    fun register(@Field("studentID") studentID:String, @Field("auth") auth:String): Call<Reception>
}