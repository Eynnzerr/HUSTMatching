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

    suspend fun loginByPassword(studentID: String, password: String) = userService.loginByPassword(studentID, password).await()
    suspend fun loginByEmail(studentID: String, auth: String) = userService.loginByEmail(studentID, auth).await()

    suspend fun getAuth(studentID: String) = userService.getAuth(studentID).await()

    suspend fun verify(studentID: String, auth: String) = userService.verify(studentID, auth).await()

    suspend fun register(studentID: String, username: String, password: String) = userService.register(studentID, username, password).await()

    suspend fun sendPostsItem(map: Map<String, String>, token: String) = userService.sendPostsItem(map, token).await()

    suspend fun sendPostsPerson(map: Map<String, String>, token: String) = userService.sendPostsPerson(map, token).await()

    suspend fun match(id:Int, token: String) = userService.match(id, token).await()

    suspend fun getPosts(token: String) = userService.getPosts(token).await()

    suspend fun test(token: String) = a(token).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    Log.d("response", "onResponse")
                    val body = response.body()
                    Log.d("response", "msg : ${response.message()}")
                    Log.d("response", "${body}")
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.d("response", "onFailure")
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    fun a(token: String):Call<com.example.hustmatching.response.Response>{
        val a = userService.test(token)
        return a
    }
}