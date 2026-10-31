package com.example.myapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout listeNotes, layoutListe, layoutVide;
    private EditText editRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String email = getIntent().getStringExtra("email");
        TextView textEmail = findViewById(R.id.textEmail);
        textEmail.setText(email);

        listeNotes = findViewById(R.id.listeNotes);
        layoutListe = findViewById(R.id.layoutListe);
        layoutVide = findViewById(R.id.layoutVide);
        editRecherche = findViewById(R.id.editRecherche);

        editRecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                afficherNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        Button boutonAjouter = findViewById(R.id.boutonAjouter);
        boutonAjouter.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        afficherNotes(editRecherche.getText().toString());
    }

    private void afficherNotes(String filtre) {
        listeNotes.removeAllViews();

        if (Note.liste.isEmpty()) {
            layoutVide.setVisibility(View.VISIBLE);
            layoutListe.setVisibility(View.GONE);
            return;
        }
        layoutVide.setVisibility(View.GONE);
        layoutListe.setVisibility(View.VISIBLE);

        String f = filtre.trim().toLowerCase();

        for (Note note : Note.liste) {
            String nomTag = (note.getTag() == null) ? "" : note.getTag().getLabel();

            boolean correspond = note.getTitre().toLowerCase().contains(f)
                    || nomTag.toLowerCase().contains(f);
            if (!correspond) continue;

            View ligne = getLayoutInflater().inflate(R.layout.item_note, listeNotes, false);
            TextView textTitre = ligne.findViewById(R.id.textTitre);
            TextView textTag = ligne.findViewById(R.id.textTag);

            textTitre.setText(note.getTitre());

            if (note.getTag() == null) {
                textTag.setVisibility(View.GONE);
            } else {
                textTag.setText(nomTag);
                textTag.setBackgroundTintList(
                        ColorStateList.valueOf(Color.parseColor(note.getTag().getCouleur())));
            }
            listeNotes.addView(ligne);
        }
    }
}