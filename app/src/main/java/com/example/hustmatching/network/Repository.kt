package com.example.hustmatching.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

object Repository {

    private val defaultDispatcher = Dispatchers.IO

    var token:String = ""

    suspend fun loginByPassword(studentID: String, password: String) =
            withContext(defaultDispatcher) {
                Network.loginByPassword(studentID, password)
            }

    suspend fun loginByEmail(studentID: String, auth: String) =
            withContext(defaultDispatcher) {
                Network.loginByEmail(studentID, auth)
            }

    suspend fun getAuth(studentID: String) =
            withContext(defaultDispatcher) {
                Network.getAuth(studentID)

            }

    suspend fun verify(studentID: String,auth:String) = withContext(defaultDispatcher){
        Network.verify(studentID, auth)
    }

    suspend fun register(studentID: String,username: String,password: String) = withContext(defaultDispatcher){
        Network.register(studentID, username, password)
    }

    suspend fun sendPostsItem(map: Map<String, String>) = withContext(defaultDispatcher){
        Network.sendPostsItem(map,"Bearer $token")
    }

    suspend fun sendPostsPerson(map: Map<String, String>) = withContext(defaultDispatcher){
        Network.sendPostsPerson(map,"Bearer $token")
    }

    suspend fun match(id:Int) = withContext(defaultDispatcher){
        Network.match(id,"Bearer $token")
    }

    suspend fun getPosts() = withContext(defaultDispatcher){
        Network.getPosts("Bearer $token")
    }

    suspend fun test() = withContext(defaultDispatcher){
        Network.test( "Bearer $token")
    }
}