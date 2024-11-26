package com.example.documentsharingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadActivity extends AppCompatActivity {

    Button selectFileButton, uploadButton;
    EditText documentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        selectFileButton = findViewById(R.id.selectFileButton);
        uploadButton = findViewById(R.id.uploadButton);
        documentTitle = findViewById(R.id.documentTitle);

        ActivityResultLauncher<Intent> selectFileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Uri fileUri = result.getData().getData();
                            uploadButton.setOnClickListener(v -> {
                                if (fileUri != null) {
                                    String title = documentTitle.getText().toString().replaceAll("[^a-zA-Z0-9]", "_");
                                    FirebaseStorage storage = FirebaseStorage.getInstance();
                                    StorageReference reference = storage.getReference("documents/" + title + ".pdf");

                                    reference.putFile(fileUri)
                                            .addOnSuccessListener(taskSnapshot ->
                                                    Toast.makeText(UploadActivity.this, "Uploaded", Toast.LENGTH_SHORT).show())
                                            .addOnFailureListener(e ->
                                                    Toast.makeText(UploadActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                                }
                            });
                        }
                    }
                });

        selectFileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            selectFileLauncher.launch(intent);
        });
    }
    @Override
    public void onBackPressed() {
        // Start DashboardActivity
        super.onBackPressed();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);


        finish();
    }
}

