package com.example.hustmatching.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hustmatching.bean.NetPost
import com.example.hustmatching.network.Repository
import com.example.hustmatching.room.PostDatabase
import com.example.hustmatching.utils.catch
import com.example.hustmatching.utils.checkCode
import kotlinx.coroutines.launch
import java.util.*

class ItemEditFrag1ViewModel : ViewModel() {
    val keywords: ArrayList<String>
    val info: ArrayList<String>
    val sended=MutableLiveData<Boolean>()
    val mid = MutableLiveData<Int>()

    //在DatePicker中选择的日期 格式：yyyy-MM-dd
    var date : String

    fun addKeyWord(keyword: String) {
        keywords.add(keyword)
    }

    fun resetKeyWord() {
        keywords.clear()
    }

    fun addInfo(information: String) {
        info.add(information)
    }

    fun addPostToDatabase(post: NetPost, context: Context) {
        Thread {
            Log.d("addToDataBase","开始向数据库存储mid=" + post.mid)
            PostDatabase.getDataBase(context).postDao.insertPost(post)
        }.start()
    }

    init {
        keywords = ArrayList()
        info = ArrayList()
        //info.add("地点")
        date = ""
    }

    fun sendPosts(map: Map<String,String>){
        Log.d("map","$map")
        viewModelScope.launch {
            try {
                val response = Repository.sendPostsItem(map)
                if (response.code == 200){
                    mid.postValue(response.data.mid)
                    sended.postValue(true)
                    Log.d("sendPosts","请求成功， mid = " + response.data.mid)
                } else{
                    checkCode(response)
                    Log.d("sendPosts","请求失败")
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

    fun sendPostsPerson(map: Map<String,String>){
        Log.d("map","$map")
        viewModelScope.launch {
            try {
                val response = Repository.sendPostsPerson(map)
                Log.d("sendPosts","msg = " + response.msg)
                Log.d("sendPosts","code = " + response.code)
                if (response.code == 200){
                    sended.postValue(true)
                    Log.d("sendPosts","请求成功， mid = " + response.data.mid)
                } else{
                    checkCode(response)
                    Log.d("sendPosts","请求失败")
                }
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

    fun test(){
        viewModelScope.launch {
            try {
                val response = Repository.test()
                Log.d("response data","$response")
                if (response.code != 200)
                    checkCode(response)
            } catch (e: Exception) {
                catch(e)
            }
        }
    }

}
