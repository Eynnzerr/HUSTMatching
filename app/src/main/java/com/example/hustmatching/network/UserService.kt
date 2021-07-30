package com.example.hustmatching.network

import com.example.hustmatching.response.DataResponse
import com.example.hustmatching.response.LoginData
import com.example.hustmatching.response.Response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST(Api.LOGIN_PWD_URL)
    fun loginByPassword(@Field("studentID") studentID: String, @Field("password") password:String): Call<DataResponse<LoginData>>

    @FormUrlEncoded
    @POST(Api.LOGIN_EMAIL_URL)
    fun loginByEmail(@Field("studentID") studentID:String, @Field("auth") auth:String): Call<DataResponse<LoginData>>

    @FormUrlEncoded
    @POST(Api.REGISTER_URL)
    fun register(@Field("studentID") studentID:String, @Field("username") userName: String, @Field("password") password: String): Call<Response>

    @FormUrlEncoded
    @POST(Api.SEND_VERIFY_URL)
    fun getAuth(@Field("studentID") studentID:String): Call<Response>

    @FormUrlEncoded
    @POST(Api.VERIFY_URL)
    fun verify(@Field("studentID") studentID:String, @Field("auth") auth:String): Call<Response>

    @FormUrlEncoded
    @POST(Api.SEND_POST)
    fun sendNetPost(@FieldMap() map: Map<String,String>): Call<Response>

}