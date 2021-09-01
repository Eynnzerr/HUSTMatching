package com.example.hustmatching.bean;

public class User {
    private String username;
    private String password;
    private String student_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public User() {
    }

    public User(String username, String password, String student_id) {
        this.username = username;
        this.password = password;
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }
}
