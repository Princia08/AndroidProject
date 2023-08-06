package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.vieww.SignupActivity;
import com.example.myproject.controller.LoginController;
import com.example.myproject.model.LoginCallback;
import com.example.myproject.model.UserModel;

public class MainActivity extends AppCompatActivity {

    private EditText mailText, passwordText;
    private Button btnLogin;

    private TextView signupLink;
    private LoginController loginController=new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailText = findViewById(R.id.mailText);
        passwordText = findViewById(R.id.passwordText);
        btnLogin = findViewById(R.id.loginButton);
        signupLink = findViewById(R.id.signupLink);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }


        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
    private void login() {
        MainActivity mainActivity = this;
        String mail = mailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        loginController.Login(mail, password, new LoginCallback() {
            @Override
            public void onSuccess(UserModel user) {
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mainActivity, "Vous êtes connectés!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mainActivity, HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Throwable error) {
                String errorr =error.getMessage();
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mainActivity,errorr, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}