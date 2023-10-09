package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.AI.ViewModel;
import com.g12.faunalencyclopedia.Search.AVLTree;


import java.util.List;
import java.util.Optional;

public class ContentActivity extends AppCompatActivity {
    private ViewModel viewModel;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int charIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Button button = findViewById(R.id.returnButton);
        TextView commonName = findViewById(R.id.commonName);
        TextView scientificName = findViewById(R.id.scientificName);
        TextView taxonomicGroup = findViewById(R.id.taxonomicGroup);
        TextView taxonomicSubgroup = findViewById(R.id.taxonomicSubgroup);
        AVLTree<Animal> animals = DataHolder.getInstance().getDataset();
        TextView description = findViewById(R.id.descriptionText);
        String authorization = getString(R.string.authorization);

        Intent intent = getIntent();
        String animalName = intent.getStringExtra("ANIMAL");
        Optional<Animal> targetAnimal = animals.get(animal -> animal.getCommon_name().equals(animalName));
        /*Optional<Animal> targetAnimal = animals.stream()
                .filter(animal -> animal.getCommon_name().equals(animalName))
                .findFirst();*/
        if (targetAnimal.isPresent()) {
            Animal animal = targetAnimal.get();
            commonName.setText(animal.getCommon_name());
            scientificName.setText(animal.getScientific_name());
            taxonomicGroup.setText(animal.getTaxonomic_group());
            taxonomicSubgroup.setText(animal.getTaxonomic_subgroup());
        }

        button.setOnClickListener(view -> {
            Intent intentBack = new Intent(ContentActivity.this, ListActivity.class);
            startActivity(intentBack);
        });

        description.setText("Generating content...");
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getCurrentCharacter().observe(this, response -> {
            charIndex = 0;
            handler.removeCallbacksAndMessages(null);
            simulateTypingEffect(description, response);
        });
        int ID = getResources().getIdentifier("Content", "string", getPackageName());
        String prompt = getString(ID, animalName);
        description.setText("");
        viewModel.fetchResponse(authorization, prompt);
    }

    private void simulateTypingEffect(final TextView textView, final String response) {
        if (charIndex <= response.length() - 1) {
            textView.append(String.valueOf(response.charAt(charIndex)));
            charIndex++;
            handler.postDelayed(() -> simulateTypingEffect(textView, response), 10);
        }
    }
}