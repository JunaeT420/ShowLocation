package com.example.user.showlocation.model;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    private String userId;
    @SerializedName("password")
    private String password;

    public User(){}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}