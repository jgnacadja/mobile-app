package com.example.myapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    private LinearLayout listeTags;
    private Tag tagChoisi = null;
    private TextView[] chipsFixes;

    private Tag[] tagsFixes = new Tag[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        EditText editTitre = findViewById(R.id.editTitre);
        EditText editDescription = findViewById(R.id.editDescription);
        listeTags = findViewById(R.id.listeTags);

        chipsFixes = new TextView[]{
                findViewById(R.id.tagFamille),
                findViewById(R.id.tagEcole),
                findViewById(R.id.tagFinance),
                findViewById(R.id.tagBoulot),
                findViewById(R.id.tagPolitique)
        };
        for (int i = 0; i < chipsFixes.length; i++) {
            TextView chip = chipsFixes[i];
            String hex = "#9AA5B1";
            if (chip.getBackgroundTintList() != null) {
                int couleur = chip.getBackgroundTintList().getDefaultColor();
                hex = String.format("#%06X", 0xFFFFFF & couleur);
            }
            Tag tag = new Tag(chip.getText().toString(), hex);

            tagsFixes[i] = tag;
            chip.setOnClickListener(v -> choisir(tag));
        }

        ImageView boutonFermer = findViewById(R.id.boutonFermer);
        boutonFermer.setOnClickListener(v -> finish());

        TextView textCreerTag = findViewById(R.id.textCreerTag);
        textCreerTag.setOnClickListener(v -> {
            Intent intent = new Intent(NoteActivity.this, TagActivity.class);
            startActivity(intent);
        });

        Button boutonValider = findViewById(R.id.boutonValider);
        boutonValider.setOnClickListener(v -> {
            String titre = editTitre.getText().toString().trim();
            String description = editDescription.getText().toString().trim();

            if (titre.isEmpty()) {
                editTitre.setError("Le titre est obligatoire");
                return;
            }

            Note.liste.add(new Note(titre, description, tagChoisi));
            finish();
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        afficherTags();
    }

    private void choisir(Tag tag) {
        tagChoisi = (tag == tagChoisi) ? null : tag;
        afficherTags();
    }

    private float transparence(Tag tag) {
        return (tagChoisi == null || tag == tagChoisi) ? 1f : 0.4f;
    }

    private void afficherTags() {
        for (int i = 0; i < chipsFixes.length; i++) {
            chipsFixes[i].setAlpha(transparence(tagsFixes[i]));
        }

        listeTags.removeAllViews();
        for (Tag tag : Tag.liste) {
            TextView chip = new TextView(this);
            chip.setText(tag.getLabel());
            chip.setTextSize(12);
            chip.setTextColor(Color.WHITE);
            chip.setBackgroundResource(R.drawable.bg_tag);
            chip.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(tag.getCouleur())));
            int horizontal = (int) (12 * getResources().getDisplayMetrics().density);
            int vertical = (int) (4 * getResources().getDisplayMetrics().density);
            chip.setPadding(horizontal, vertical, horizontal, vertical);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 20, 0);
            chip.setLayoutParams(params);

            chip.setAlpha(transparence(tag));
            chip.setOnClickListener(v -> choisir(tag));
            listeTags.addView(chip);
        }
    }
}