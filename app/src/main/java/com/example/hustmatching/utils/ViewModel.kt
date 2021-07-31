package com.example.hustmatching.utils

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.base.BaseApplication
import com.example.hustmatching.bean.Info
import com.example.hustmatching.network.Repository
import com.example.hustmatching.response.Response
import com.example.hustmatching.ui.login.LoginActivity
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
    if(response.code==201){
        relogin()
    } else{
        Toast.makeText(BaseApplication.getContext(),response.msg, Toast.LENGTH_SHORT).show()
    }

}

fun ViewModel.relogin(){
    viewModelScope.launch {
        try {
            val info = getInfo()
            val response = Repository.loginByPassword(info.studentID,info.password )
            Log.d("login","response.code:" + response.code)
            if (response.code == 200) {
                Repository.token = response.data.token
            } else {
                Toast.makeText(BaseApplication.getContext(),"登录信息失效，请重新登录", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setClass(BaseApplication.getContext(),LoginActivity::class.java)
                BaseApplication.getContext().startActivity(intent)
            }
        } catch (e: Exception) {
            catch(e)
            Toast.makeText(BaseApplication.getContext(),"登录信息失效，请重新登录", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setClass(BaseApplication.getContext(),LoginActivity::class.java)
            BaseApplication.getContext().startActivity(intent)
        }
    }
}

fun ViewModel.getInfo() : Info{
    var studentID=""
    var password=""
    SecurityUtil.getEncryptedSharedPreferences(BaseApplication.getContext()).apply {
        studentID = getString("studentID","")!!
        password = getString("password","")!!
    }
    return Info(studentID, password)
}

fun ViewModel.saveInfo(studentID:String, password:String){
    SecurityUtil.getEncryptedSharedPreferences(BaseApplication.getContext()).edit {
        putString("studentID",studentID)
        putString("password",password)
    }
}