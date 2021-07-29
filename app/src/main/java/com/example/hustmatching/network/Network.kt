package com.example.hustmatching.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object Network {
    private val userService = ServiceCreator.create<UserService>()

    suspend fun login(username: String, password: String) = userService.login(username, password).await()

    suspend fun getAuth(studentID:String) = userService.getAuth(studentID).await()

    suspend fun getDailyWeather() = userService.getDailyWeather().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    Log.d("coroutine","onresponse: ${Thread.currentThread().name}")
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}