package com.example.myproject.controller;

import android.util.Log;
import android.view.inputmethod.InputMethodSession;

import com.example.myproject.model.BaseUrl;
import com.example.myproject.model.Event;
import com.example.myproject.model.Site;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventController {
    OkHttpClient client = new OkHttpClient();
    String baseUrl = new BaseUrl().getValue();

    public void getEventTomorrow(EventSiteCallback callback) {
        String apiUrl = baseUrl + "/event/tomorrow";

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);
                        List<Event> eventList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonEvent = jsonArray.getJSONObject(i);
                            Event event = new Event();
                            event.setId(jsonEvent.optString("id"));
                            event.setLabel(jsonEvent.optString("label"));
                            event.setDescription(jsonEvent.optString("description"));
                            event.setDate_event(jsonEvent.optString("date_event"));

                            eventList.add(event);
                        }

                        callback.onSuccess(eventList);
                    } catch (Exception e) {
                        Log.e("JSON Parsing Error", "Error parsing JSON: " + e.getMessage());
                        callback.onFailure(e);
                    }
                } else {
                    callback.onFailure(new Exception(response.message()));
                }
            }
        });
    }

    public void getAll(EventSiteCallback callback) {
        String apiUrl = baseUrl + "/event";

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);
                        List<Event> eventList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonEvent = jsonArray.getJSONObject(i);
                            Event event = new Event();
                            event.setId(jsonEvent.optString("id"));
                            event.setLabel(jsonEvent.optString("label"));
                            event.setDescription(jsonEvent.optString("description"));
                            event.setDate_event(jsonEvent.optString("date_event"));

                            eventList.add(event);
                        }

                        callback.onSuccess(eventList);
                    } catch (Exception e) {
                        Log.e("JSON Parsing Error", "Error parsing JSON: " + e.getMessage());
                        callback.onFailure(e);
                    }
                } else {
                    callback.onFailure(new Exception(response.message()));
                }
            }
        });
    }
}
