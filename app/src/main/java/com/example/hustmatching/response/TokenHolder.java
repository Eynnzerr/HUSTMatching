package com.example.hustmatching.response;

public class TokenHolder {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenHolder(String token) {
        this.token = token;
    }

    public TokenHolder() {
    }

    @Override
    public String toString() {
        return "TokenHolder{" +
                "token='" + token + '\'' +
                '}';
    }
}
