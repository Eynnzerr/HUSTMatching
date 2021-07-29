package com.example.hustmatching.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*

class ItemEditFrag1ViewModel : ViewModel() {
    val keywords: ArrayList<String>
    val info: ArrayList<String>

    //在DatePicker中选择的日期 格式：yyyy-MM-dd
    var date : String

    fun addKeyWord(keyword: String) {
        keywords.add(keyword)
    }

    fun addInfo(information: String) {
        info.add(information)
    }

    init {
        keywords = ArrayList()
        info = ArrayList()
        //info.add("地点")
        date = ""
    }

}