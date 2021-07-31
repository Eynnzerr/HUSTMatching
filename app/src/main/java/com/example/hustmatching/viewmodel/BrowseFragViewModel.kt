package com.example.hustmatching.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.hustmatching.base.BaseApplication
import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.network.Repository
import com.example.hustmatching.room.PostDatabase
import com.example.hustmatching.utils.NetPostUtil
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class BrowseFragViewModel : ViewModel() {
    private var matchedPosts: MutableList<Array<NetPost?>>? = null

    /*
    fun getMatchedPosts(): List<Array<NetPost?>> {
        //模拟假数据用于测试，后期可以删除
        matchedPosts = ArrayList()
        for (i in 0..9) {
            val netPosts = arrayOfNulls<NetPost>(2)
            val myPost = NetPost()
            myPost.classification = NetPostUtil.SEARCH_ITEM
            myPost.title = "我的发布$i"
            val yourPost = NetPost()
            yourPost.classification = NetPostUtil.SEARCH_ITEM
            yourPost.title = "匹配的发布$i"
            val tags: MutableList<String> = ArrayList()
            for (j in 0..2) {
                tags.add("关键词$j")
            }
            yourPost.tags = tags
            netPosts[0] = myPost
            netPosts[1] = yourPost
            yourPost.time = "2021-7-30 上午"
            yourPost.location = "韵酒"
            yourPost.date = "2021-7-31"
            yourPost.qq = "123456789"
            yourPost.phone = "18055557780"
            (matchedPosts as ArrayList<Array<NetPost?>>).add(netPosts)
        }
        return matchedPosts as ArrayList<Array<NetPost?>>
    }*/


    val matchedPostsLive = MutableLiveData<MutableList<Array<NetPost?>>?> ()

    fun findMatch(myPost: NetPost, mid: Int){
        Log.d("mid","$mid")
        viewModelScope.launch {
            try {
                val response = Repository.match(mid)
                Log.d("findMatch","code:" + response.code);
                Log.d("findMatch","msg:" + response.msg);
                if (response.code == 200){
                    Log.d("findMatch","响应成功");
                    if (response.data != null) {
                        Log.d("findMatch","匹配标题" + response.data.title);
                    }
                    var matchedPost: NetPost = NetPost()
                    matchedPost.classification = response.data.classification
                    matchedPost.date = response.data.date
                    matchedPost.title = response.data.title
                    matchedPost.detail = response.data.detail
                    matchedPost.location = response.data.location
                    matchedPost.time = response.data.time
                    matchedPost.qq = response.data.qq
                    matchedPost.phone = response.data.phone
                    val tags = ArrayList<String>()
                    tags.add(response.data.tag1)
                    tags.add(response.data.tag2)
                    tags.add(response.data.tag3)
                    matchedPost.tags = tags
                    val netPosts = arrayOfNulls<NetPost>(2)
                    netPosts[0] = myPost
                    netPosts[1] = matchedPost
                    (matchedPosts as ArrayList<Array<NetPost?>>).add(netPosts)
                    matchedPostsLive.postValue(matchedPosts)
                    //包装返回的数据为二元组并加入列表
                } else{
                    Log.d("findMatch","响应失败");
                    checkCode(response)
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }


    val posts : LiveData<List<NetPost>> = PostDatabase.getDataBase(BaseApplication.getContext()).postDao.allPostsLive
    //val postDao = PostDatabase.getDataBase(BaseApplication.getContext()).postDao

    /*fun loadPosts() {
        Log.d("adapter", "post value before")
        thread {
            val list= postDao.allPosts
            posts.postValue(list)
            Log.d("adapter", "post value")

        }
    }*/
}