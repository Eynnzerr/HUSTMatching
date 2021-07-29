package com.example.hustmatching.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//发送验证码至邮箱
interface AuthenService {
    @FormUrlEncoded
    @POST(Api.AUTHEN_URL)
    fun sendAuthen(@Field("studentID") studentID:String): Call<Response>
}