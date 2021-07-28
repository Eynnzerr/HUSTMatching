package com.example.hustmatching.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginFragViewModel extends ViewModel {
    //保存用户界面输入的账号和密码，实现了双向绑定
    private MutableLiveData<String> account;
    private MutableLiveData<String> password;

    public LoginFragViewModel() {
        account = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }

    public MutableLiveData<String> getAccount() {
        return account;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }
}
