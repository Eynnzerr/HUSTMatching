package com.example.hustmatching.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmailRegisterFragViewModel extends ViewModel {
    //保存用户界面输入的邮箱和验证码，实现了双向绑定
    private MutableLiveData<String> email;
    private MutableLiveData<String> authen;

    public EmailRegisterFragViewModel() {
        email = new MutableLiveData<>();
        authen = new MutableLiveData<>();
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getAuthen() {
        return authen;
    }
}
