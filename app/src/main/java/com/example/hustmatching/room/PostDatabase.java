package com.example.hustmatching.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.hustmatching.bean.NetPost;

@TypeConverters({TagConverter.class})
@Database(entities = {NetPost.class},version = 2,exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {
    //singleton
    private static final String DATABASE_NAME = "songlist_database";//复制的名字忘改了
    private static PostDatabase INSTANCE;
    public static synchronized PostDatabase getDataBase(Context context)
    {
        if( INSTANCE == null )
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PostDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract PostDao getPostDao();
}
