package com.example.hustmatching.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.base.BaseApplication
import com.example.hustmatching.network.Repository
import com.example.hustmatching.utils.SecurityUtil
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import kotlinx.coroutines.launch

class RegisterFragViewModel(application: Application) : AndroidViewModel(application) {

    //保存用户界面输入的邮箱和验证码，实现了双向绑定
    val email = MutableLiveData<String>()
    val authen = MutableLiveData<String>()
    val verified = MutableLiveData<Boolean>()


    fun getAuth(studentID: String) {
        viewModelScope.launch {
            try {
                val response = Repository.getAuth(studentID)
                if (response.code != 0)
                    checkCode(response)
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

    fun verify(studentID: String, auth: String) {
        viewModelScope.launch {
            try {
                val response = Repository.verify(studentID, auth)
                Log.d("verify","response.code:" + response.code)
                Log.d("verify","response.msg:" + response.msg)
                if (response.code == 200) {
                    Log.d("verify:","post true")
                    verified.postValue(true)
                } else
                    checkCode(response)
            } catch (e: Exception) {
                catch(e)
            }
        }

    }
}