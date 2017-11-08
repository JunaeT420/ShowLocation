package com.example.user.showlocation.model;

import android.text.TextUtils;

/**
 * Created by USER on 10/17/2017.
 */

public class LoginResponse {
    private Data data;
    private String msg;
    private String error;

    public Data getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public String getError()
    {
        return error;
    }

    public boolean isError() {
        if (TextUtils.isEmpty(error))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public class Data {
        private String email;
        private String name;
        private String createdAt;
        private long id;
        private String token;
        private String updatedAt;

        public String getEmail() { return email; }
        public void setEmail(String value) { this.email = value; }

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String value) { this.createdAt = value; }

        public long getId() { return id; }
        public void setId(long value) { this.id = value; }

        public String getToken() { return token; }
        public void setToken(String value) { this.token = value; }

        public String getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(String value) { this.updatedAt = value; }

        @Override
        public String toString() {
            return "Data{" +
                    "email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", id=" + id +
                    ", token='" + token + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }
    }
}
