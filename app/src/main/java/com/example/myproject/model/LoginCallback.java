package com.example.myproject.model;

public interface LoginCallback {
    void onSuccess(UserModel user);
    void onFailure(Throwable error);
}
