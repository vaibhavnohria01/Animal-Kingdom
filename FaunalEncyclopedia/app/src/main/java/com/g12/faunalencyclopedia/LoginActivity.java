package com.g12.faunalencyclopedia;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);

        Button buttonLogin = findViewById(R.id.login);
        Button openSignuppage = findViewById(R.id.signup);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if(validCredentials(username,password)){
                    Intent intent = new Intent(LoginActivity.this, MainApp.class);
                    startActivity(intent);

                } else{
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        openSignuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, signup.class);
                startActivity(intent);
            }
        });
    }

    private boolean validCredentials(String username, String password){

        //String savedUsername = "user123";
        //String savedPassword = "password123";

        String savedUsername;
        String savedPassword;

        try {
            FileInputStream fileInputStream = openFileInput("user_details.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            savedUsername = bufferedReader.readLine();
            savedPassword = bufferedReader.readLine();

            bufferedReader.close();

            return username.equals(savedUsername) && password.equals(savedPassword);


        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}