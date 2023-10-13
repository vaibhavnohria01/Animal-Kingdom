package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.FileWriter;
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
                //Andrew: After signing up, enter the app directly
                Intent intent = new Intent(signup.this, ListActivity.class);
                intent.putExtra("EMAIL", user.getEmail());
                startActivity(intent);
            }
        });

       Button home_screen = findViewById(R.id.button);
       home_screen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(signup.this, LoginActivity.class);
               startActivity(intent);
           }
       });
    }
    private void saveDetailsToFile(User user){
        try{
            String userData = "Username: "+user.getUsername()+"||Email: "+user.getEmail()+"||Password: "+user.getPassword();
            FileWriter myWriter = new FileWriter("user_details.txt");
            //String file = "user_details.txt";
            //FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
            //fos.write(userData.getBytes());
            //fos.close();
            myWriter.write(userData);
            myWriter.close();
            Toast.makeText(this, "SignUp successful!! ", Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(this, "SignUp Unsuccessful. Try again! ", Toast.LENGTH_SHORT).show();
        }
    }


}