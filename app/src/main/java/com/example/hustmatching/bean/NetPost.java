package com.example.hustmatching.bean;

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
}
