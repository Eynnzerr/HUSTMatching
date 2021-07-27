package com.example.hustmatching.retrofit

import com.example.hustmatching.bean.Reception
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST(Api.LOGIN_URL)
    fun login(@Field("username") userName:String, @Field("password") password:String): Call<Reception>
}