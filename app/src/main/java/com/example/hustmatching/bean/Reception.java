package com.example.hustmatching.bean;

import com.google.gson.annotations.SerializedName;

public class Reception {

    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;

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
