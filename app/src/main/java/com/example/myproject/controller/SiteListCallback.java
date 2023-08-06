package com.example.myproject.controller;

import com.example.myproject.model.Site;
import com.example.myproject.model.UserModel;

import java.util.List;

public interface SiteListCallback {
    void onSuccess(List<Site> site);
    void onFailure(Throwable error);
}
