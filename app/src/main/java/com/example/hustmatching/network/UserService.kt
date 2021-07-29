package com.example.hustmatching.network

import com.example.hustmatching.response.DailyResopnse
import com.example.hustmatching.response.DataResponse
import com.example.hustmatching.response.LoginData
import com.example.hustmatching.response.Response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST(Api.LOGIN_URL)
    fun login(@Field("username") userName:String, @Field("password") password:String): Call<DataResponse<LoginData>>

    @FormUrlEncoded
    @POST(Api.REGISTER_URL)
    fun register(@Field("studentID") studentID:String, @Field("auth") auth:String): Call<Response>

    @FormUrlEncoded
    @POST(Api.SEND_VERIFY_URL)
    fun getAuth(@Field("studentID") studentID:String): Call<Response>

    @GET("v2.5/${Api.TOKEN}/114.3439,30.5003/daily.json?dailysteps=2")
    fun getDailyWeather():Call<DailyResopnse>

}