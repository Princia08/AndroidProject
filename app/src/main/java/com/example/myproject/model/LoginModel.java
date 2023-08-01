package com.example.myproject.model;

public class LoginModel {
    public String login(String username, String password) {
        return (username.isEmpty() || password.isEmpty()) ? "Please enter both username and password." : "Login successfull!";
    }
}
