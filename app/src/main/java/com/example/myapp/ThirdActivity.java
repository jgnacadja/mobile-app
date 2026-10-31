package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Récupérer le bouton flottant rose (+)
        FloatingActionButton btnPlus = findViewById(R.id.floatingActionButton);

        // Quand on clique sur le bouton (+), on ouvre AddNoteActivity
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}