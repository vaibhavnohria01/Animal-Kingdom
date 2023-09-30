package com.g12.faunalencyclopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        StorageReference dataRef = storageRef.child("data/dataset.json");

        dataRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                String metadataStr = "Metadata: "
                        + "\nBucket: " + storageMetadata.getBucket()
                        + "\nName: " + storageMetadata.getName()
                        + "\nPath: " + storageMetadata.getPath()
                        + "\nSize: " + storageMetadata.getSizeBytes() + " bytes"
                        + "\nContent Type: " + storageMetadata.getContentType()
                        + "\nCreation Time: " + storageMetadata.getCreationTimeMillis()
                        + "\nUpdated Time: " + storageMetadata.getUpdatedTimeMillis();

                Log.d("Metadata", metadataStr);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
            }
        });
    }
}