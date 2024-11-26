package com.example.documentsharingapp;

import android.content.Intent;

import android.os.Bundle;

import static android.content.Intent.ACTION_VIEW;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StudentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard); // Replace with your layout

        String fileName = "my_document.pdf"; // Initialize fileName

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("documents/" + fileName);

        reference.getDownloadUrl().addOnSuccessListener(uri -> {
            Intent intent = new Intent(ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        });
    }
    @Override
    public void onBackPressed() {
        // Start DashboardActivity
        super.onBackPressed();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);

        // Finish StudentDashboardActivity
        finish();
    }
}
