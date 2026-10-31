package com.example.myapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity3 extends AppCompatActivity {

    private ChipGroup chipGroup;
    private Chip chipCreateTag;

    private final ActivityResultLauncher<Intent> createTagLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String tagName = result.getData().getStringExtra("tag_name");
                    int tagColor = result.getData().getIntExtra("tag_color", Color.parseColor("#E91E63"));
                    addNewChip(tagName, tagColor);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView btnClose = findViewById(R.id.btnClose);
        MaterialButton btnAjouter = findViewById(R.id.btnAjouter);
        TextInputEditText edtTitre = findViewById(R.id.edtTitre);
        TextInputEditText edtDescription = findViewById(R.id.edtDescription);

        chipGroup = findViewById(R.id.chipGroup);
        chipCreateTag = findViewById(R.id.chipCreateTag);


        btnClose.setOnClickListener(v -> finish());


        chipCreateTag.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            createTagLauncher.launch(intent);
        });


        btnAjouter.setOnClickListener(v -> {
            String titre = edtTitre.getText().toString().trim();

            if (titre.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un titre", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Note ajoutée !", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void addNewChip(String name, int color) {
        Chip newChip = new Chip(this);
        newChip.setText(name);
        newChip.setChipBackgroundColor(ColorStateList.valueOf(color));
        newChip.setTextColor(Color.WHITE);
        newChip.setCheckable(true);
        newChip.setClickable(true);


        int index = chipGroup.indexOfChild(chipCreateTag);
        chipGroup.addView(newChip, index);
    }
}