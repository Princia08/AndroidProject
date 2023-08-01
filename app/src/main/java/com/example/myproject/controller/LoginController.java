package com.example.myproject.controller;

import com.example.myproject.model.LoginModel;

public class LoginController {
    private LoginModel loginModel;

    public LoginController(LoginModel model) {
        this.loginModel = model;
    }

    public String login(String username, String password) {
        return loginModel.login(username, password);
    }
}
