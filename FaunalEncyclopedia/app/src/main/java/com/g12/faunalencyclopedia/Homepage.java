package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Andrew: Button to go to the login page
        Button button = findViewById(R.id.LoginButton);
        Button toLogin = findViewById(R.id.to_login);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(Homepage.this, LoginActivity.class);
            startActivity(intent);
        });
        toLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Homepage.this, signup.class);
            startActivity(intent);
        });
    }
}