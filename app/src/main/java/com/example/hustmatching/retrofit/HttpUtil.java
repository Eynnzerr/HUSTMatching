package com.example.hustmatching.retrofit;


import com.example.hustmatching.bean.Token;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description:封装了retrofit网络请求的一些方法。通过调用getXXXCall()方法可以获得对应的Call对象，接着调用enqueue发起请求即可。
 */
public class HttpUtil {
    private static final int DEFAULT_TIMEOUT = 8; //连接 超时的时间，单位：秒

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static Retrofit retrofit;

    private synchronized static Retrofit getRetrofit() {
        if( retrofit == null ) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Call<Response> getRegisterCall(String studentID, String auth) {
        RegisterService registerService = getRetrofit().create(RegisterService.class);
        return registerService.register(studentID, auth);
    }

    public static Call<DataResponse<Token>> getLoginCall(String userName, String password) {
        LoginService loginService = getRetrofit().create(LoginService.class);
        return loginService.login(userName, password);
    }

}
