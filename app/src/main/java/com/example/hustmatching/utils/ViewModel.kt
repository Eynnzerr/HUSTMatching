package com.example.hustmatching.utils

import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.base.BaseApplication
import com.example.hustmatching.bean.Info
import com.example.hustmatching.response.Response
import kotlinx.coroutines.launch
import java.lang.Exception

fun ViewModel.catch(e: Exception){
    if (e.message=="response body is null"){
        Toast.makeText(BaseApplication.getContext(),"服务器发生错误", Toast.LENGTH_SHORT).show()
    } else{
        Toast.makeText(BaseApplication.getContext(),"请求失败", Toast.LENGTH_SHORT).show()
    }
}

fun ViewModel.checkCode(response: Response){
    Toast.makeText(BaseApplication.getContext(),response.msg, Toast.LENGTH_SHORT).show()

}

fun ViewModel.relogin(){
    viewModelScope.launch {

    }
}

fun ViewModel.getInfo() : Info{
    var username=""
    var password=""
    SecurityUtil.getEncryptedSharedPreferences(BaseApplication.getContext()).apply {
        username = getString("username","")!!
        password = getString("password","")!!
    }
    return Info(username, password)
}

fun ViewModel.saveInfo(username:String, password:String){
    SecurityUtil.getEncryptedSharedPreferences(BaseApplication.getContext()).edit {
        putString("username",username)
        putString("password",password)
    }
}