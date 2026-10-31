package com.example.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Récupérer les éléments graphiques du XML grâce à leurs IDs
        EditText champEmail = findViewById(R.id.editTextTextEmailAddress);
        Button boutonOk = findViewById(R.id.button);
        TextView messageResultat = findViewById(R.id.textView2);

        // 2. Écouter le clic sur le bouton OK
        boutonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le texte tapé par l'utilisateur
                String emailSaisi = champEmail.getText().toString();

                // Vérification basique (un email doit contenir un @ et un point)
                if (emailSaisi.contains("@") && emailSaisi.contains(".")) {

                    // SUCCÈS : Créer le pont vers la nouvelle page
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    // Transmettre l'email saisi pour l'afficher sur la 2ème page
                    intent.putExtra("EMAIL_SAISI", emailSaisi);
                    // Lancer la page
                    startActivity(intent);

                } else {
                    // ERREUR : Afficher en rouge
                    messageResultat.setText("Email invalide");
                    messageResultat.setTextColor(Color.RED);
                }
            }
        });

        // 3. Faire disparaître l'erreur quand on clique dans le champ email
        champEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remettre le sous-titre par défaut en gris
                messageResultat.setText("Organisez votre journée et restez zen");
                messageResultat.setTextColor(Color.GRAY);
            }
        });
    }
}