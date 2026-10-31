package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView texteEmailAffichage = findViewById(R.id.textViewUserEmail);
        String emailRecu = getIntent().getStringExtra("EMAIL_SAISI");
        if (emailRecu != null) {
            texteEmailAffichage.setText(emailRecu);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Créer l'Intent pour aller vers la 3ème page
                Intent intent = new Intent(WelcomeActivity.this, ThirdActivity.class);
                startActivity(intent);

                finish();
            }
        }, 5000); // 5000 = 5 secondes
    }
}