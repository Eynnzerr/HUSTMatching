package com.example.hustmatching.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hustmatching.bean.NetPost;

@Database(entities = {NetPost.class},version = 1,exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {
    //singleton
    private static final String DATABASE_NAME = "songlist_database";
    private static PostDatabase INSTANCE;
    public static synchronized PostDatabase getDataBase(Context context)
    {
        if( INSTANCE == null )
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PostDatabase.class,DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }
    public abstract PostDao getPostDao();
}
