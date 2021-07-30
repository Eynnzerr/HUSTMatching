package com.example.hustmatching.room;

import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TagConverter {

    @TypeConverter
    public String tagListToString(List<String> list)
    {
        return GsonInstance.getInstance().getGson().toJson(list);
    }

    @TypeConverter
    public List<String> StringToSongList(String json)
    {
        Type listType = new TypeToken<List<String>>(){}.getType();
        return GsonInstance.getInstance().getGson().fromJson(json,listType);
    }
}
