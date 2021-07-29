package com.example.hustmatching.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmailRegisterFragViewModel : ViewModel() {
    //保存用户界面输入的邮箱和验证码，实现了双向绑定
    val email: MutableLiveData<String>
    val authen: MutableLiveData<String>

    init {
        email = MutableLiveData()
        authen = MutableLiveData()
    }
}