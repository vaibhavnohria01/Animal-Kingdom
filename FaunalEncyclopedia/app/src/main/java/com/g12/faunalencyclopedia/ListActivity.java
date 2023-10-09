package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.Search.AVLTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        AVLTree<Animal> dataset = DataHolder.getInstance().getDataset();
        List<String> animalNames = new ArrayList<>();


        /*for (Animal animal : dataset) {
            animalNames.add(animal.getCommon_name());
        }*/
        dataset.inorder(Animal -> {
            animalNames.add(Animal.getCommon_name());
        });

        //ArrayList<Animal> dataset = (ArrayList<Animal>) getIntent().getExtras().getSerializable("DATASET");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, animalNames);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(ListActivity.this, ContentActivity.class);
            intent.putExtra("ANIMAL", animalNames.get(i));
            startActivity(intent);
        });
    }
}