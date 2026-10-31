package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home_empty extends AppCompatActivity {

    private TextView recupmail;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_empty);

        recupmail=findViewById(R.id.recup_email);
        btnAdd = findViewById(R.id.bntAdd);

        //Récupérer le intent qui a lancé cette activitée
        Intent intent = getIntent();

        String emailRecu = intent.getStringExtra("EMAIL_UTILISATEUR");

        // 4. On vérifie qu'on a bien reçu quelque chose et on l'affiche
        if (emailRecu != null) {
            recupmail.setText(emailRecu);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_empty.this, AjoutNote.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}