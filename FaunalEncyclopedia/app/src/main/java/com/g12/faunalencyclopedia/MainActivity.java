package com.g12.faunalencyclopedia;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.Data.DataLoader;
import com.g12.faunalencyclopedia.Search.AVLTree;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Andrew: Added a handler and a runnable to load data from Firebase
    private static final long INTERVAL = 60000;
    private Handler dataLoadingHandler = new Handler();
    private DataLoader dataLoader = new DataLoader(this);;
    private Runnable dataLoadingRunnable = new Runnable(){

        @Override
        public void run() {
            System.out.println("Loading data...");
            dataLoader.loadDataSet(new DataLoader.OnDataLoadedCallback() {

                @Override
                public void onSuccess(AVLTree<Animal> dataset) {
                    System.out.println("Data loaded successfully");
                    DataHolder.getInstance().setDataset(dataset);

                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(MainActivity.this, "Failed to load data: " + exception.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            dataLoadingHandler.postDelayed(this, INTERVAL);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Ensure you remove any callbacks to prevent memory leaks
        dataLoadingHandler.removeCallbacks(dataLoadingRunnable);
    }

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

        dataLoadingHandler.post(dataLoadingRunnable);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            //intent.putExtra("DATASET", dataset);
            startActivity(intent);
        });

    }
}