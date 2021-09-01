package com.example.hustmatching.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.base.BaseApplication
import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.network.Repository
import com.example.hustmatching.room.PostDatabase
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import com.example.hustmatching.utils.saveInfo
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MyReleaseFragViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MyReleaseFragViewModel"

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
            val list= postDao.allPosts
            posts.postValue(list)
            Log.d("adapter", "post value")
            Log.d("adapter", "$list")
        }
    }

    fun refresh(){
        viewModelScope.launch {
            try {
                val response = Repository.getPosts()
                if (response.code == 0) {
                    Log.d(TAG, "refresh: msg" + response.msg)
                    val a = response.data
                    // TODO: list<PostResponse> to List<NetPost>
                    var list =ArrayList<NetPost>()
                    for (postResponse in a) {
                        var matchedPost = NetPost()
                        matchedPost.classification = postResponse.classification
                        matchedPost.date = postResponse.date
                        matchedPost.title = postResponse.title
                        matchedPost.detail = postResponse.detail
                        matchedPost.location = postResponse.location
                        matchedPost.time = postResponse.time
                        matchedPost.qq = postResponse.qq
                        matchedPost.phone = postResponse.phone
                        val tags = ArrayList<String>()
                        tags.add(postResponse.key1)
                        tags.add(postResponse.key2)
                        tags.add(postResponse.key3)
                        matchedPost.tags = tags
                        list.add(matchedPost)
                        Log.d(TAG, "refresh: 找到一条我的发布：" + matchedPost)
                    }
                    posts.postValue(list)
                    savePosts()
                } else {
                    checkCode(response)
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
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