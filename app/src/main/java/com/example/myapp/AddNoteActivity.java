package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Récupérer le bouton "Créer un tag"
        Button btnCreerTag = findViewById(R.id.btnCreerTag);

        // Ajouter l'action au clic
        btnCreerTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNoteActivity.this, CreateTagActivity.class);
                startActivity(intent);
            }
        });
    }
}