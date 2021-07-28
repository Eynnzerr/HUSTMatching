package com.example.hustmatching.bean;

import com.google.gson.annotations.SerializedName;

//由于pm把联系方式只保留了qq和手机号，再对联系方式做这样的封装很多余，故弃用
@Deprecated
public class Contact {

    @SerializedName("qq")
    private Integer qq;
    @SerializedName("phone")
    private Long phone;

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

}
