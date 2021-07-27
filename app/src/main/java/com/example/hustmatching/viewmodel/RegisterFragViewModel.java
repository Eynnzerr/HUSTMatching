package com.example.hustmatching.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class RegisterFragViewModel extends ViewModel {
    private MutableLiveData<String> email;
    private MutableLiveData<String> authen;

    public RegisterFragViewModel() {
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
