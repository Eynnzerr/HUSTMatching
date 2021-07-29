package com.example.hustmatching.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.network.Repository
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import kotlinx.coroutines.launch

class EmailRegisterFragViewModel : ViewModel() {
    //保存用户界面输入的邮箱和验证码，实现了双向绑定
    val studentID = MutableLiveData<String>()
    val auth = MutableLiveData<String>()
    val checked = MutableLiveData<Boolean>()

    fun login(studentID: String, auth:String) {
        viewModelScope.launch {
            try {
                val response = Repository.loginByEmail(studentID, auth)
                if (response.code == 200) {
                    Repository.token = response.data.token
                    checked.postValue(true)
                } else {
                    checkCode(response)
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }
    fun getAuth(studentID: String) {
        viewModelScope.launch {
            try {
                val response = Repository.getAuth(studentID)
                if (response.code != 200)
                    checkCode(response)
            } catch (e: Exception) {
                catch(e)
            }
        }
    }
}