package com.example.hustmatching.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.bean.User
import com.example.hustmatching.network.Repository
import com.example.hustmatching.room.GsonInstance
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import com.example.hustmatching.utils.saveInfo
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class SettingFragViewModel:ViewModel() {
    //保存用户界面输入的账号和密码，实现了双向绑定
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val registered = MutableLiveData<Boolean>()
    val logined = MutableLiveData<Boolean>()

    fun register(studentID: String,username: String,password: String){
        viewModelScope.launch {
            try {
                val user = User(username, password, studentID)
                //val response = Repository.register(studentID, username, password)
                val response = Repository.registerByJson(user)
                Log.d("register","response.code:" + response.code)
                Log.d("register","response.msg:" + response.msg)
                if (response.code == 0) {
                    registered.postValue(true)
                } else {
                    checkCode(response)
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

    fun login(studentID: String, password: String) {
        viewModelScope.launch {
            try {
                val response = Repository.loginByPassword(studentID, password)
                if (response.code == 0) {
                    Repository.token = response.data.token//保存返回的token
                    Log.d("login", "token: " + Repository.token)
                    saveInfo(studentID, password)
                    logined.postValue(true)
                } else {
                    checkCode(response)
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

}