package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.View.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private EditText userText, passwordText;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userText = findViewById(R.id.etUsername);
        passwordText = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = userText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        // Perform simple validation (you can add more complex validation here)
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
        } else {
            // Perform login process here (e.g., check credentials against a database or API)
            // For this simple example, we'll just display a success message.
            // Toast.makeText(this, "Login successfull!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}