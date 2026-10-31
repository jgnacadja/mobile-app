package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 1. Récupérer l'élément de texte où on va afficher l'email
        TextView texteEmailAffichage = findViewById(R.id.textViewUserEmail);

        // 2. Récupérer l'email qui a été transmis par MainActivity
        String emailRecu = getIntent().getStringExtra("EMAIL_SAISI");

        // 3. Remplacer "user@yopmail.com" par le vrai email tapé par l'utilisateur
        if (emailRecu != null) {
            texteEmailAffichage.setText(emailRecu);
        }
    }
}