package com.example.myproject.vieww;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.HomeActivity;
import com.example.myproject.MainActivity;
import com.example.myproject.R;
import com.example.myproject.controller.SignupController;
import com.example.myproject.model.LoginCallback;
import com.example.myproject.model.UserModel;

public class SignupActivity extends AppCompatActivity {
    private SignupController signupController=new SignupController();
    private EditText nomText, prenomText, adresseText, mailText, passwordText;
    private Button signupButton;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        nomText= findViewById(R.id.nomText);
        prenomText= findViewById(R.id.prenomText);
        adresseText= findViewById(R.id.adressText);
        mailText= findViewById(R.id.mailText);
        passwordText= findViewById(R.id.passwordText);
        signupButton= findViewById(R.id.signupButton);
        loginLink= findViewById(R.id.loginLink);

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private boolean isValidEmail(String mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private void signup() {
        SignupActivity signupActivity = this;
        String nom = nomText.getText().toString().trim();
        String prenom = prenomText.getText().toString().trim();
        String adresse = adresseText.getText().toString().trim();
        String mail = mailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

            if (!isValidEmail(mail)) Toast.makeText(signupActivity, "Veuillez entrer un adresse email valide!", Toast.LENGTH_SHORT).show();
            else {
                if(nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || mail.isEmpty() || password.isEmpty())
                    Toast.makeText(signupActivity, "Veuillez remplir tous les champs!", Toast.LENGTH_SHORT).show();
                else {
                    signupController.Signup(nom, prenom, mail, password, adresse, new LoginCallback() {
                        @Override
                        public void onSuccess(UserModel user) {
                            signupActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(signupActivity, "Vous êtes bien inscrit(e)s!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(signupActivity, HomeActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Throwable error) {
                            String errorr = error.getMessage();
                            signupActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(signupActivity, errorr, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
    }
    private void login() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}