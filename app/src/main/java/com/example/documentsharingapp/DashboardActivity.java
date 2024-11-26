package com.example.documentsharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = "DashboardActivity";
    private ListenerRegistration snapshotListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button studentButton = findViewById(R.id.studentDashboard); // Assuming you have a button with id studentButton
        Button facultyButton = findViewById(R.id.facultyDashboard); // Assuming you have a button with id facultyButton

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to StudentDashboardActivity
                startActivity(new Intent(DashboardActivity.this, StudentDashboardActivity.class));
                finish();
            }
        });

        facultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to UploadActivity
                startActivity(new Intent(DashboardActivity.this, UploadActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Remove the snapshot listener when the activity stops to avoid memory leaks
        if (snapshotListener != null) {
            snapshotListener.remove();
        }
    }
}