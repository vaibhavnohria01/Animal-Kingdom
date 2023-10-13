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

               /* if(validCredentials(username,password)){
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    // Andrew: Let app know who is logged in
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);

                } else{
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }*/

                // Andrew: Custom feature FB-Auth
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                        // Andrew: Let app know who is logged in
                        intent.putExtra("EMAIL", user.getEmail());
                        startActivity(intent);
                    } else {
                        // Andrew: If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "The password is incorrect.", Toast.LENGTH_SHORT).show();
                    }
                });

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
        String savedPassword;


        try {
            FileInputStream fileInputStream = openFileInput("user_details.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            //InputStreamReader inputStreamReader = new InputStreamReader(getResources().openRawResource(R.raw.user_details));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Andrew: Are you gonna only check one user?
            //savedUsername = bufferedReader.readLine();
            //savedPassword = bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals(username) || tokens[1].equals(username)){
                    savedPassword = tokens[2];
                    bufferedReader.close();

                    // Andrew: The username is already the same
                    //return username.equals(savedUsername) && password.equals(savedPassword);
                    return password.equals(savedPassword);
                }
            }

            return false;


        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}