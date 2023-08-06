package com.example.myproject.controller;
import com.example.myproject.model.LoginCallback;
import com.example.myproject.model.UserModel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginController {
    OkHttpClient client = new OkHttpClient();

    public void Login(String mail, String password, LoginCallback callback) {
        String apiUrl = "http://172.22.12.236:8080/api/user/auth";
        RequestBody body = new FormBody.Builder()
                .add("mail", mail)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle network errors
                callback.onFailure(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonUser = new JSONObject(responseData);
                        UserModel userResponse= new UserModel();
                        userResponse.setId(jsonUser.optString("id"));
                        callback.onSuccess(userResponse);
                    } catch (Exception e) {
                        callback.onFailure(e);
                    }
                } else {
                    try {
                        callback.onFailure(new Exception((new JSONObject(response.body().string())).optString("message")));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        //return userResponse;
    }
}
