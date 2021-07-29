package com.example.hustmatching.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.hustmatching.R;
import com.example.hustmatching.viewmodel.RegisterFragViewModel;
import com.example.hustmatching.viewmodel.TestViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TestViewModel viewModel = new ViewModelProvider(this).get(TestViewModel.class);

        Log.d("coroutine","main before: "+Thread.currentThread().getName());

        viewModel.getDailyWeather();
        Log.d("coroutine","main after: "+Thread.currentThread().getName());

    }
}