package com.g12.faunalencyclopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is a simple demo to show how to get data
        TextView text = findViewById(R.id.data);

        DataLoader dataLoader = new DataLoader(this);
        dataLoader.loadDataSet(new DataLoader.OnDataLoadedCallback() {
            @Override
            public void onSuccess(List<Animal> dataset) {
                text.setText(dataset.get(0).toString());
            }

            @Override
            public void onFailure(Exception exception) {
                // Handle error
                text.setText("Failed to load data: " + exception.getMessage());
            }
        });
    }
}