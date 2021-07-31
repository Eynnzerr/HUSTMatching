package com.example.hustmatching.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hustmatching.bean.NetPost;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insertPost(NetPost... netPosts);

    @Update
    void updatePost(NetPost... netPosts);

    @Delete
    void deletePost(NetPost... netPosts);

    @Query("DELETE FROM NetPost")
    void deleteAllPosts();

    //查询特定项
    //@Query("SELECT * FROM NetPost WHERE title = :title")
    //NetPost getNetPost(String title);

    @Query("SELECT * FROM NetPost")
    List<NetPost> getAllPosts();

    @Query("SELECT * FROM NetPost")
    LiveData<List<NetPost>> getAllPostsLive();
}
