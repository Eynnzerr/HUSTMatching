package com.example.hustmatching.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.utils.NetPostUtil
import java.util.*

class MyReleaseFragViewModel : ViewModel() {
    private var posts: MutableList<NetPost>? = null
    fun getPosts(): List<NetPost> {
        //模拟假数据，日后可删
        posts = ArrayList()
        for (i in 0..9) {
            val myPost = NetPost()
            myPost.classification = NetPostUtil.SEARCH_ITEM
            myPost.title = "我的发布$i"
            val tags: MutableList<String> = ArrayList()
            for (j in 0..5) {
                tags.add("关键词$j")
            }
            myPost.tags = tags
            (posts as ArrayList<NetPost>).add(myPost)
        }
        return posts as ArrayList<NetPost>
    }
}