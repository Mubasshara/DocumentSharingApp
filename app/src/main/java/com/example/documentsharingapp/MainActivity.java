package com.example.documentsharingapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);


        // Check if the user is logged in, or redirect to LoginActivity
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // User is logged in, navigate to DashboardActivity
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            // User is not logged in, navigate to LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish(); // Close the MainActivity to prevent back navigation to it
    }
}
