package com.g12.faunalencyclopedia;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.Data.DataLoader;
import com.g12.faunalencyclopedia.Search.AVLTree;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signup = findViewById(R.id.signUp);
        Button login = findViewById(R.id.login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.g12.faunalencyclopedia.signup.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // This is a simple demo to show how to get data
        TextView text = findViewById(R.id.load);
        Button button = findViewById(R.id.toList);

        DataLoader dataLoader = new DataLoader(this);
        dataLoader.loadDataSet(new DataLoader.OnDataLoadedCallback() {

            @Override
            public void onSuccess(AVLTree<Animal> dataset) {
                text.setText("Loaded");
                DataHolder.getInstance().setDataset(dataset);
                button.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    //intent.putExtra("DATASET", dataset);
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(Exception exception) {
                // Handle error
                text.setText("Failed to load data: " + exception.getMessage());
            }
        });



    }
}