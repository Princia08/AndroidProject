package com.example.myproject.controller;

import com.example.myproject.model.Event;
import com.example.myproject.model.Site;

import java.util.List;

public interface EventSiteCallback {
    void onSuccess(List<Event> events);
    void onFailure(Throwable error);
}
