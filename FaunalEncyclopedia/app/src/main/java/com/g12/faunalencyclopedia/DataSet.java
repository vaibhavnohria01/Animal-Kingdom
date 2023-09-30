package com.g12.faunalencyclopedia;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class DataSet {
    private File getDataSet(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        StorageReference dataRef = storageRef.child("data/dataset.json");
        File dataset;

        {
            try {
                dataset = File.createTempFile("dataset", "json");
                dataRef.getFile(dataset).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>(){

                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
                return dataset;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseData(File dataset){
        Gson gson = new Gson();
        Type type = new TypeToken<DataSet>() {}.getType();
        //DataSet dataset = gson.fromJson(new FileReader(localFile), type);

    }

}
