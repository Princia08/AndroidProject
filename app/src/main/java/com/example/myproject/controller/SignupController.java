package com.example.myproject.controller;
import com.example.myproject.model.BaseUrl;
import com.example.myproject.model.LoginCallback;
import com.example.myproject.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupController {
    OkHttpClient client = new OkHttpClient();
    String baseUrl=new BaseUrl().getValue();

    public void Signup(String nom, String prenom, String mail, String password, String adresse, LoginCallback callback) {
        String apiUrl = baseUrl+"/user/signup";
        RequestBody body = new FormBody.Builder()
                .add("nom", nom)
                .add("prenom", prenom)
                .add("adresse", adresse)
                .add("mail", mail)
                .add("mdp", password)
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
                    callback.onFailure(new Exception(response.message()));
                }
            }
        });
    }
}
