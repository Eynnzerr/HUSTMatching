package com.example.hustmatching.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

object Repository {

    private val defaultDispatcher = Dispatchers.IO

    suspend fun login(username: String, password: String) {
        withContext(defaultDispatcher){
            Network.login(username, password)
        }
    }

    suspend fun getAuth(studentID:String) =
        withContext(defaultDispatcher){
            Network.getAuth(studentID)
        }

    suspend fun getDailyWeather() = withContext(defaultDispatcher){
        Log.d("coroutine","reposity before: ${Thread.currentThread().name}")
        Network.getDailyWeather()

    }


}