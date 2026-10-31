package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText mail;
    private TextView msg ,Textmail, welcome , TextEmail;
    private Button btn;
    private ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        final Pattern GMAIL_PATTERN =
                Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$");

        mail=findViewById(R.id.email);
        msg=findViewById(R.id.mess);
        btn=findViewById(R.id.btnok);

        welcome=findViewById(R.id.bienvenue);
        TextEmail=findViewById(R.id.recup_email);
        Textmail=findViewById(R.id.text3);
        progressBar=findViewById(R.id.progressBar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mail.getText().toString().trim();

                if (!GMAIL_PATTERN.matcher(input).matches()) {
                    msg.setText("Veuillez fournir un email valide");
                } else {
                    msg.setText("");
                    // 1. CACHER l'interface d'authentification
                    mail.setVisibility(View.GONE);
                    msg.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
                    Textmail.setVisibility(View.GONE);

                    // 3. Mettre l'email saisi dans le TextView
                    TextEmail.setText(input);
                    TextEmail.setVisibility(View.VISIBLE);
                    welcome.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);


                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Création de l'Intent (de MainActivity vers SuccessActivity)
                            Intent intent = new Intent(MainActivity.this, Home_empty.class);

                            // Optionnel : Tu peux passer l'email à la nouvelle activité
                            intent.putExtra("EMAIL_UTILISATEUR", input);

                            // Lancer la nouvelle activité
                            startActivity(intent);

                            // Optionnel : Fermer l'activité actuelle pour ne pas y revenir si on fait "Retour"
                            finish();
                        }
                    }, 5000);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}