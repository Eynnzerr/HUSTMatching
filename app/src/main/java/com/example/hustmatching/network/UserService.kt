package com.example.hustmatching.network

import com.example.hustmatching.response.*
import retrofit2.Call
import retrofit2.http.*


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
    @POST(Api.SEARCH_ITEM_URL)
    fun sendPostsItem(@FieldMap() map: Map<String,String>, @Header("Authorization") token:String): Call<DataResponse<IdResponse>>

    @FormUrlEncoded
    @POST(Api.SEARCH_PERSON_URL)
    fun sendPostsPerson(@FieldMap() map: Map<String,String>, @Header("Authorization") token:String): Call<DataResponse<IdResponse>>

    @FormUrlEncoded
    @POST(Api.MATCH_URL)
    fun match(@Field("mid") id:Int, @Header("Authorization") token:String): Call<DataResponse<PostResponese>>


    @POST(Api.TEST_URL)
    fun test( @Header("Authorization") token:String): Call<Response>
}