package com.g12.faunalencyclopedia.Data;

import android.content.Context;

import androidx.annotation.NonNull;

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
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private Context context;

    public DataLoader(Context context) {
        this.context = context;
    }
    public void loadDataSet(DataLoader.OnDataLoadedCallback callback) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference dataRef = storageRef.child("data/datalist.json");
        ArrayList<Animal> dataset = new ArrayList<>();
        File localFile;
        {
            try {
                localFile = File.createTempFile("datalist", "json");
                dataRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<List<String>>>() {}.getType();
                        List<List<String>> datalist;

                        try {
                            datalist = gson.fromJson(new FileReader(localFile), type);
                            for (List<String> data : datalist){
                                Animal animal = new Animal(data);
                                dataset.add(animal);
                            }
                            callback.onSuccess(dataset);
                        } catch (FileNotFoundException e) {
                            callback.onFailure(e);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        callback.onFailure(exception);
                    }
                });
            } catch (IOException e) {
                callback.onFailure(e);
            }
        }
    }

    public interface OnDataLoadedCallback {
        void onSuccess(ArrayList<Animal> dataset);
        void onFailure(Exception exception);
    }
}

