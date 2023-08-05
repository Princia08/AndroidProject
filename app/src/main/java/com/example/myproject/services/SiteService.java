package com.example.myproject.services;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myproject.R;

import org.json.JSONObject;

public class SiteService {
    private Context context;

    public SiteService() {
    }

    public SiteService(Context context) {
        this.context = context;
    }

    public void getSites(){
        String url = context.getString(R.string.api_base_url)+"/site";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("responsee ohhhh  : ", String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responsee ohhhh  : ", String.valueOf(error));
            }
        });
    }
}
