package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText champEmail = findViewById(R.id.editTextTextEmailAddress2);
        TextView textErreur = findViewById(R.id.textErreur);
        Button boutonOk = findViewById(R.id.button2);

        boutonOk.setOnClickListener(v -> {
            String email = champEmail.getText().toString().trim();

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                textErreur.setVisibility(View.VISIBLE);
            }
        });

        champEmail.setOnClickListener(v -> textErreur.setVisibility(View.INVISIBLE));
        champEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                textErreur.setVisibility(View.INVISIBLE);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}