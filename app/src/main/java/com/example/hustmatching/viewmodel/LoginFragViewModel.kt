package com.example.hustmatching.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.network.Repository
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import com.example.hustmatching.utils.getInfo
import com.example.hustmatching.utils.saveInfo
import kotlinx.coroutines.launch

class LoginFragViewModel : ViewModel() {
    //保存用户界面输入的账号和密码，实现了双向绑定
    val studentID = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val checked = MutableLiveData<Boolean>()

    fun login(studentID: String, password: String) {
        viewModelScope.launch {
            try {
                val response = Repository.loginByPassword(studentID, password)
                Log.d("login","response.code:" + response.code)
                if (response.code == 200) {
                    Repository.token = response.data.token
                    saveInfo(studentID, password)
                    checked.postValue(true)
                } else {
                    checkCode(response)
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

    fun tryLogin(){
        val info = getInfo()
        if (info.studentID!="" && info.password!= ""){
            login(info.studentID,info.password)
        }
    }

}