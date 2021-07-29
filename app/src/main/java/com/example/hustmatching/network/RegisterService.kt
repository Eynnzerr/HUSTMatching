package com.example.hustmatching.network

import com.example.hustmatching.response.Response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {
    @FormUrlEncoded
    @POST(Api.REGISTER_URL)
    fun register(@Field("studentID") studentID:String, @Field("auth") auth:String): Call<Response>
}