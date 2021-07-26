package com.example.hustmatching.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @description: receive a user's data from the API
 */
public class UserJson {

    @SerializedName("data")
    private User data;
    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
