package com.example.hustmatching.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hustmatching.base.BaseApplication
import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.room.PostDatabase
import kotlin.concurrent.thread

class MyReleaseFragViewModel(application: Application) : AndroidViewModel(application) {

    val posts = MutableLiveData<List<NetPost>>()
    val postDao = PostDatabase.getDataBase(BaseApplication.getContext()).postDao

    //    fun getPosts(): List<NetPost> {
//        //模拟假数据，日后可删
//        posts = ArrayList()
//        for (i in 0..9) {
//            val myPost = NetPost()
//            myPost.classification = NetPostUtil.SEARCH_ITEM
//            myPost.title = "我的发布$i"
//            val tags: MutableList<String> = ArrayList()
//            for (j in 0..5) {
//                tags.add("关键词$j")
//            }
//            myPost.tags = tags
//            (posts as ArrayList<NetPost>).add(myPost)
//        }
//        return posts as ArrayList<NetPost>
//    }
    fun loadPosts() {
        Log.d("adapter", "post value before")
        thread {
            posts.postValue(postDao.allPosts)
            Log.d("adapter", "post value")

        }
    }

    fun refresh(){

    }

    fun savePosts(){
        postDao.deleteAllPosts()
        posts.value?.let {
            for ( each in it ){
                postDao.insertPost(each)
            }
        }
    }
}