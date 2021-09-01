package com.example.hustmatching.network

import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.bean.NetPostEx
import com.example.hustmatching.bean.User
import com.example.hustmatching.response.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface UserService {
    @FormUrlEncoded
    @POST(Api.LOGIN_PWD_URL)
    fun loginByPassword(@Field("student_id") studentID: String, @Field("password") password:String): Call<DataResponse<LoginData>>

    @FormUrlEncoded
    @POST(Api.LOGIN_EMAIL_URL)
    fun loginByEmail(@Field("student_id") studentID:String, @Field("auth") auth:String): Call<DataResponse<LoginData>>

    @FormUrlEncoded
    @POST(Api.REGISTER_URL)
    fun register(@Field("student_id") studentID:String, @Field("username") userName: String, @Field("password") password: String): Call<Response>

    //由于retrofit添加了Gson转换器，这里参数可以直接使用User类型，最后由gson转化为json字符串。
    @POST(Api.REGISTER_URL)
    @Headers("Content-type:application/json;charset=UTF-8")
    fun registerByJson(@Body user: User): Call<Response>

    @FormUrlEncoded
    @POST(Api.SEND_VERIFY_URL)
    fun getAuth(@Field("student_id") studentID:String): Call<Response>

    @FormUrlEncoded
    @POST(Api.VERIFY_URL)
    fun verify(@Field("student_id") studentID:String, @Field("auth") auth:String): Call<Response>

    @FormUrlEncoded
    @POST(Api.SEARCH_ITEM_URL)
    fun sendPostsItem(@FieldMap() map: Map<String,String>, @Header("Authorization") token:String): Call<DataResponse<IdResponse>>

    @FormUrlEncoded
    @POST(Api.SEARCH_PERSON_URL)
    fun sendPostsPerson(@FieldMap() map: Map<String,String>, @Header("Authorization") token:String): Call<DataResponse<IdResponse>>

    @POST(Api.PUBLISH_URL)
    @Headers("Content-type:application/json;charset=UTF-8")
    fun publish(@Body post: NetPostEx, @Header("token") token: String): Call<DataResponse<IdResponse>>

    @FormUrlEncoded
    @POST(Api.MATCH_URL)
    fun match(@Field("mid") id:Int, @Header("token") token:String): Call<DataResponse<PostResponese>>

    @GET(Api.GET_POSTS_URL)
    fun getPosts(@Header("token") token:String): Call<DataResponse<List<PostResponese>>>

    @POST(Api.TEST_URL)
    fun test( @Header("token") token:String): Call<Response>
}