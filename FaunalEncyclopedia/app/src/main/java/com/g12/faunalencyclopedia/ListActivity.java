package com.g12.faunalencyclopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.g12.faunalencyclopedia.Data.Animal;
import com.g12.faunalencyclopedia.Data.DataHolder;
import com.g12.faunalencyclopedia.Search.AVLTree;
import com.g12.faunalencyclopedia.Search.HashtagQuery;
import com.g12.faunalencyclopedia.Search.SearchParser;
import com.g12.faunalencyclopedia.Search.SearchTokenizer;
import com.g12.faunalencyclopedia.Search.WordQuery;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author UID:u7630167 Name: Yihang Zhu
 */
public class ListActivity extends AppCompatActivity {
    private SearchView searchView;
    private  AVLTree<Animal> animalTree;

    // Andrew: Updating the list view when the activity resumes
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("Updating list");
        animalTree = DataHolder.getInstance().getDataset(); // Assign the dataset to animalTree
        List<String> animalNames = new ArrayList<>();

        animalTree.inorder(Animal -> {
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

        searchView = findViewById(R.id.SearchBar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (animalTree != null) { // Check if the tree is populated
                    filterList(s);
                } else {
                    Toast.makeText(ListActivity.this, "The tree is empty. Please populate it first.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        bottomHistory.setOnClickListener(view -> {
            Intent intentToHistory = new Intent(ListActivity.this, HistoryActivity.class);
            startActivity(intentToHistory);
        });
    }

    private void filterList(String text) {
        List<Animal> filterList = new ArrayList<>();

        // Tokenize the input string
        SearchTokenizer tokenizer = new SearchTokenizer();
        List<String> tokens = tokenizer.tokenize(text);

        // Parse the tokens
        SearchParser parser = new SearchParser(tokens);
        Object query = parser.parse();

        if (query instanceof HashtagQuery) { // Search by class
            String searchClass = ((HashtagQuery) query).getHashtag().toLowerCase();
            animalTree.inorder(animal -> {
                // Assuming you have a method getClass() in the Animal class
                if (animal.getTaxonomic_group().toLowerCase().equals(searchClass)) {
                    filterList.add(animal);
                }
            });
        } else if (query instanceof WordQuery) { // Search by common name or scientific name
            String searchTerm = ((WordQuery) query).getWord().toLowerCase();
            animalTree.inorder(animal -> {
                if (animal.getCommon_name().toLowerCase().contains(searchTerm) ||
                        animal.getScientific_name().toLowerCase().contains(searchTerm)) {
                    filterList.add(animal);
                }
            });
        }

        // Create a new list of animal names from the filtered list
        List<String> filteredAnimalNames = new ArrayList<>();
        for (Animal animal : filterList) {
            filteredAnimalNames.add(animal.getCommon_name());
        }

        // Update the ListView adapter with the new list of animal names
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredAnimalNames);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        if(filterList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
        }
}}