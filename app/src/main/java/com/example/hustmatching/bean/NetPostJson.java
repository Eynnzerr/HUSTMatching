package com.example.hustmatching.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @description: receive data which contains matches of a user from API
 */
public class NetPostJson {

    @SerializedName("data")
    private DataDTO data;
    @SerializedName("code")
    private Integer code;
    @SerializedName("msg")
    private String msg;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
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

    public static class DataDTO {
        @SerializedName("matches")
        private List<NetPost> matches;

        public List<NetPost> getMatches() {
            return matches;
        }

        public void setMatches(List<NetPost> matches) {
            this.matches = matches;
        }

    }
}
