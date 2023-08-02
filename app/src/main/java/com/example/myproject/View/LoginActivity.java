package com.example.myproject.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class LoginActivity extends AppCompatActivity {

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        test = findViewById(R.id.hello);
        show();
    }

    private void show() {
        String testt = test.getText().toString();
        Toast.makeText(this, testt, Toast.LENGTH_SHORT).show();
    }
}