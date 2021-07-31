package com.example.hustmatching.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.hustmatching.room.TagConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
@TypeConverters({TagConverter.class})
public class NetPost implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
    @ColumnInfo(name = "classification")
    @SerializedName("classification")
    private String classification;
    @ColumnInfo(name = "tags")
    @SerializedName("tags")
    private List<String> tags;
    @ColumnInfo(name = "detail")
    @SerializedName("detail")
    private String detail;
    @ColumnInfo(name = "qq")
    @SerializedName("qq")
    private String qq;
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    private String phone;
    @ColumnInfo(name = "date")
    @SerializedName("date")
    private String date;//文章发布的日期：yyyy-MM-dd
    @ColumnInfo(name = "time")
    @SerializedName("time")
    private String time;//在编辑栏填写的日期：yyyy-MM-dd 下午
    @ColumnInfo(name = "location")
    @SerializedName("location")
    private String location;
    @ColumnInfo(name = "uniqueCode")
    private int mid;//标识一篇发布的唯一ID，不赋初始值，而是在发布成功上传至后端后的回调中用后端传的值赋值。作为匹配的唯一需要参数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("title:");
        stringBuilder.append(title);
        stringBuilder.append("\nclassification:");
        stringBuilder.append(classification);
        stringBuilder.append("\ndetails:");
        stringBuilder.append(detail);
        stringBuilder.append("\nqq:");
        stringBuilder.append(qq);
        stringBuilder.append("\nphone:");
        stringBuilder.append(phone);
        stringBuilder.append("\ndate:");
        stringBuilder.append(date);
        stringBuilder.append("\ntags:");
        stringBuilder.append(tags);
        return stringBuilder.toString();
    }
}
