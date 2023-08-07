package com.example.myproject.controller;

import android.util.Log;

import com.example.myproject.model.BaseUrl;
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

public class SiteController {
    OkHttpClient client = new OkHttpClient();
    String baseUrl=new BaseUrl().getValue();

    // Fonction pour récupérer la liste des sites
    public void getSiteList(SiteListCallback callback) {
        String apiUrl = baseUrl+"/site/";

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Gérer les erreurs de réseau
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);
                        List<Site> siteList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonSite = jsonArray.getJSONObject(i);
                            Site site = new Site();
                            site.setId(jsonSite.optString("id"));
                            site.setLabel(jsonSite.optString("label"));
                            site.setDescription(jsonSite.optString("description"));
                            site.setImage(jsonSite.optString("image"));
                            site.setVideo(jsonSite.optString("video"));

                            siteList.add(site);
                        }

                        callback.onSuccess(siteList);
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
