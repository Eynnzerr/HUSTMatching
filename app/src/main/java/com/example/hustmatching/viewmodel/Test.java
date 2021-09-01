package com.example.hustmatching.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.room.PostDatabase;

import java.util.List;

public class Test {

    private LiveData<List<NetPost>> myPosts;
    Context context;

    public Test() {
        myPosts = PostDatabase.getDataBase(context).getPostDao().getAllPostsLive();
    }

    public void startMatch() {

    }
}
