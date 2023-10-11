package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class signup extends AppCompatActivity {

    private EditText emailid;
    private EditText username;
    private EditText password;
    private Button Savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailid = findViewById(R.id.emailid);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Savebutton = findViewById(R.id.Savebutton);

        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Email = emailid.getText().toString();
                String Password = password.getText().toString();

                UserFactory userFactory;
                userFactory = new NewUser();
                User user = userFactory.createUser(Username,Email,Password);
                saveDetailsToFile(user);
            }
        });

       Button home_screen = findViewById(R.id.button);
       home_screen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(signup.this, MainActivity.class);
               startActivity(intent);
           }
       });
    }
    private void saveDetailsToFile(User user){
        try{
            String userData = "Username: "+user.getUsername()+"||Email: "+user.getEmail()+"||Password: "+user.getPassword();
            String file = "user_details.txt";
            FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
            fos.write(userData.getBytes());
            fos.close();
            Toast.makeText(this, "SignUp successful!! ", Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(this, "SignUp Unsuccessful. Try again! ", Toast.LENGTH_SHORT).show();
        }
    }


}