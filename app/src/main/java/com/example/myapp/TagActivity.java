package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TagActivity extends AppCompatActivity {

    private String couleurChoisie = "";
    private ArrayList<View> pastilles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        EditText editLabel = findViewById(R.id.editLabel);

        ImageView boutonRetour = findViewById(R.id.boutonRetour);
        boutonRetour.setOnClickListener(v -> finish());

        LinearLayout[] rangees = {findViewById(R.id.rangee1), findViewById(R.id.rangee2)};
        for (LinearLayout rangee : rangees) {
            for (int i = 0; i < rangee.getChildCount(); i++) {
                View pastille = rangee.getChildAt(i);
                pastilles.add(pastille);
                pastille.setOnClickListener(v -> choisirCouleur(pastille));
            }
        }

        Button boutonEnregistrer = findViewById(R.id.boutonEnregistrer);
        boutonEnregistrer.setOnClickListener(v -> {
            String label = editLabel.getText().toString().trim();

            if (label.isEmpty()) {
                editLabel.setError("Le label est obligatoire");
                return;
            }
            if (couleurChoisie.isEmpty()) {
                Toast.makeText(this, "Choisissez une couleur", Toast.LENGTH_SHORT).show();
                return;
            }

            Tag.liste.add(new Tag(label, couleurChoisie));   // on enregistre dans la liste
            Toast.makeText(this, "Tag enregistré", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void choisirCouleur(View choisie) {
        for (View p : pastilles) {
            p.setScaleX(1f);
            p.setScaleY(1f);
        }
        choisie.setScaleX(1.3f);
        choisie.setScaleY(1.3f);

        int couleur = choisie.getBackgroundTintList().getDefaultColor();
        couleurChoisie = String.format("#%06X", 0xFFFFFF & couleur);
    }
}