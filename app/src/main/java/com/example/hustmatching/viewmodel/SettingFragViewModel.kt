package com.example.hustmatching.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.network.Repository
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import com.example.hustmatching.utils.saveInfo
import kotlinx.coroutines.launch

class SettingFragViewModel:ViewModel() {
    //保存用户界面输入的账号和密码，实现了双向绑定
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val registered = MutableLiveData<Boolean>()
    val logined = MutableLiveData<Boolean>()

    fun register(studentID: String,username: String,password: String){
        viewModelScope.launch {
            try {
                val response = Repository.register(studentID, username, password)
                if (response.code == 200) {
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
                if (response.code == 200) {
                    Repository.token = response.data.token
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