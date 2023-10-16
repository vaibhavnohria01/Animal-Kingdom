package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.Search.AVLTree;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
/**
 * @author UID:u7630167 Name: Yihang Zhu
 */
public class ListActivity extends AppCompatActivity {
    // Andrew: Updating the list view when the activity resumes
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("Updating list");
        AVLTree<Animal> dataset = DataHolder.getInstance().getDataset();
        List<String> animalNames = new ArrayList<>();

        dataset.inorder(Animal -> {
            animalNames.add(Animal.getCommon_name());
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, animalNames);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intentToContent = new Intent(ListActivity.this, ContentActivity.class);
            intentToContent.putExtra("ANIMAL", animalNames.get(i));
            startActivity(intentToContent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button bottomHistory = findViewById(R.id.bottom_history);


        bottomHistory.setOnClickListener(view -> {
            Intent intentToHistory = new Intent(ListActivity.this, HistoryActivity.class);
            startActivity(intentToHistory);
        });
    }
}