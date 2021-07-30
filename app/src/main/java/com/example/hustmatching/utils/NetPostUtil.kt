package com.example.hustmatching.utils

import android.content.Context
import android.content.SharedPreferences

object NetPostUtil {
    const val SEARCH_ITEM : String = "#寻失物"
    const val SEARCH_LOST_PERSON : String = "#寻失主"
    const val SEARCH_OTHERS : String = "#寻其他人"

    fun getID(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("ui.main.MainActivity", 0)
        return sharedPreferences.getString("ID", "")
    }
}
