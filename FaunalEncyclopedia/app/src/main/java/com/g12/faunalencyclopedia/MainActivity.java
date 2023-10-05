package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.Data.DataLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is a simple demo to show how to get data
        TextView text = findViewById(R.id.data);
        Button button = findViewById(R.id.toList);

        DataLoader dataLoader = new DataLoader(this);
        dataLoader.loadDataSet(new DataLoader.OnDataLoadedCallback() {

            @Override
            public void onSuccess(ArrayList<Animal> dataset) {
                text.setText(dataset.get(0).toString());
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