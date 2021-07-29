package com.example.hustmatching.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.network.Repository
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {
    fun getAuth(studentID: String) {
        viewModelScope.launch {
            Log.d("coroutine", Repository.getAuth(studentID).toString())
        }
    }

    fun getDailyWeather() {
        Log.d("coroutine","vm before: ${Thread.currentThread().name}")
        viewModelScope.launch {
            Log.d("coroutine","scope before: ${Thread.currentThread().name}")
            Log.d("coroutine", Repository.getDailyWeather().toString())
            Log.d("coroutine", "2")
            Log.d("coroutine","scope after: ${Thread.currentThread().name}")
        }
        Log.d("coroutine","vm after: ${Thread.currentThread().name}")

    }
}