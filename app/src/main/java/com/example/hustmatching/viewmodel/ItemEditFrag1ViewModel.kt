package com.example.hustmatching.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*

class ItemEditFrag1ViewModel : ViewModel() {
    val keywords: ArrayList<String>
    val info: ArrayList<String>
    fun addKeyWord(keyword: String) {
        keywords.add(keyword)
    }

    fun addInfo(information: String) {
        info.add(information)
    }

    init {
        keywords = ArrayList()
        keywords.add("品类")
        keywords.add("品牌")
        keywords.add("外形特点")
        info = ArrayList()
        info.add("地点")
    }
}