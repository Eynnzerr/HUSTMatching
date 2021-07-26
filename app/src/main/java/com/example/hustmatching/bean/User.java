package com.example.hustmatching.bean;

import com.google.gson.annotations.SerializedName;

public class User {

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
        @SerializedName("name")
        private String name;
        @SerializedName("sNo")
        private String sNo;
        @SerializedName("contact")
        private ContactDTO contact;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSNo() {
            return sNo;
        }

        public void setSNo(String sNo) {
            this.sNo = sNo;
        }

        public ContactDTO getContact() {
            return contact;
        }

        public void setContact(ContactDTO contact) {
            this.contact = contact;
        }

        public static class ContactDTO {
            @SerializedName("qq")
            private Long qq;
            @SerializedName("wechat")
            private String wechat;
            @SerializedName("phone")
            private Long phone;
            @SerializedName("email")
            private String email;

            public Long getQq() {
                return qq;
            }

            public void setQq(Long qq) {
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
    }
}
