package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TagCreate extends AppCompatActivity {
    private String selectedColor = "#FF5B74";
    private EditText etLabel;
    private Button btnSave;
    private ImageButton BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tag_create);

        etLabel = findViewById(R.id.et_label);
        btnSave = findViewById(R.id.btn_save);

        // --- GESTION DES COULEURS ---
        // Quand on clique sur une couleur, on change la variable selectedColor
        findViewById(R.id.color_rose).setOnClickListener(v -> selectedColor = "#FF5B74");
        findViewById(R.id.color_violet_clair).setOnClickListener(v -> selectedColor = "#D39CFE");
        findViewById(R.id.color_violet_fonce).setOnClickListener(v -> selectedColor = "#A133FF");
        findViewById(R.id.color_bleu_nuit).setOnClickListener(v -> selectedColor = "#1F1B3D");
        findViewById(R.id.color_orange).setOnClickListener(v -> selectedColor = "#FF7B00");
        findViewById(R.id.color_teal).setOnClickListener(v -> selectedColor = "#00BFA5");
        findViewById(R.id.color_cyan).setOnClickListener(v -> selectedColor = "#00B0FF");
        findViewById(R.id.color_marron).setOnClickListener(v -> selectedColor = "#A5745A");
        findViewById(R.id.color_rouge).setOnClickListener(v -> selectedColor = "#FF3B5C");
        findViewById(R.id.color_indigo).setOnClickListener(v -> selectedColor = "#6A45FF");

        btnSave.setOnClickListener(v -> {
            String label = etLabel.getText().toString().trim();

            if (label.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un label", Toast.LENGTH_SHORT).show();
                return;
            }

            // On prépare l'Intent pour renvoyer les données
            Intent returnIntent = new Intent();
            returnIntent.putExtra("TAG_NAME", label);
            returnIntent.putExtra("TAG_COLOR", selectedColor);

            // On dit que tout s'est bien passé et on renvoie les données
            setResult(RESULT_OK, returnIntent);

            // On ferme cette activité pour revenir à AjoutNote
            finish();
        });

        BtnBack=findViewById(R.id.btn_back);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}