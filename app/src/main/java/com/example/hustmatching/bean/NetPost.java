package com.example.hustmatching.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetPost {

    @SerializedName("title")
    private String title;
    @SerializedName("classification")
    private String classification;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("detail")
    private String detail;
    @SerializedName("qq")
    private String qq;
    @SerializedName("phone")
    private String phone;
    @SerializedName("date")
    private String date;//文章发布的日期：yyyy-MM-dd
    @SerializedName("time")
    private String time;//在编辑栏填写的日期：yyyy-MM-dd 下午
    @SerializedName("location")
    private String location;

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
