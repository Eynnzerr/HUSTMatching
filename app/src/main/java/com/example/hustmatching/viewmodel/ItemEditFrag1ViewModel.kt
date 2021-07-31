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

    fun sendPosts(map: Map<String,String>){
        Log.d("map","$map")
        viewModelScope.launch {
            try {
                val response = Repository.sendPostsItem(map)
                if (response.code == 200){
                    sended.postValue(true)
                } else{
                    checkCode(response)
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