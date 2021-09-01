package com.example.hustmatching.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.room.PostDatabase;

import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private LiveData<List<NetPost>> netPostsLive;

    public TestViewModel(@NonNull Application application) {
        super(application);
        netPostsLive = PostDatabase.getDataBase(application).getPostDao().getAllPostsLive();
    }

    public LiveData<List<NetPost>> getNetPostsLive() {
        return netPostsLive;
    }
}
