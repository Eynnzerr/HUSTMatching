package com.example.hustmatching.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginFragViewModel : ViewModel() {
    //保存用户界面输入的账号和密码，实现了双向绑定
    val account: MutableLiveData<String>
    val password: MutableLiveData<String>

    init {
        account = MutableLiveData()
        password = MutableLiveData()
    }
}