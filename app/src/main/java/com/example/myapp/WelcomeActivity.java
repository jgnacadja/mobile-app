package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String email = getIntent().getStringExtra("email");
        TextView textEmail = findViewById(R.id.textEmail);
        textEmail.setText(email);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        }, 5000);
    }
}