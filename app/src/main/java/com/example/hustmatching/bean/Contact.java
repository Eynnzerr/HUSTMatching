package com.example.hustmatching.bean;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("qq")
    private Integer qq;
    @SerializedName("wechat")
    private String wechat;
    @SerializedName("phone")
    private Long phone;
    @SerializedName("email")
    private String email;

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
