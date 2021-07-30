package com.example.hustmatching.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.room.PostDatabase
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

    fun addPostToDatabase(post: NetPost, context: Context) {
        Thread {
            PostDatabase.getDataBase(context).postDao.insertPost(post)
        }.start()
    }

    init {
        keywords = ArrayList()
        info = ArrayList()
        //info.add("地点")
        date = ""
    }

}