package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class AjoutNote extends AppCompatActivity {
    private String selectedTagName = "Aucun";
    private String selectedTagColor = "#D9D9D9";
    private Button BtnTag ,BtnFamille , BtnEcole ,BtnFinance , BtnPolitique,BtnBoulot, BtnAjouter ;
    private EditText titre , desciption;
    private ImageButton BtnClose;


    private ActivityResultLauncher<Intent> tagLauncher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout_note);

        // 1. Initialisation du launcher (ce qui se passe au retour de TagCreate)
        tagLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String tagName = data.getStringExtra("TAG_NAME");
                        String tagColor = data.getStringExtra("TAG_COLOR");

                        // Si on a bien reçu les données, on appelle la méthode
                        if (tagName != null && tagColor != null) {
                            addNewTagToScreen(tagName, tagColor);
                        }
                    }
                }
        );

        // 2. Clic sur le bouton "Créer un tag"
        BtnTag = findViewById(R.id.createTag);
        BtnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AjoutNote.this, TagCreate.class);
                // ⚠️ On utilise tagLauncher.launch() et non plus startActivity()
                tagLauncher.launch(i);
            }
        });

        //Creation des notes
        titre=findViewById(R.id.et_titre);
        desciption=findViewById(R.id.et_description);
        BtnAjouter=findViewById(R.id.btn_ajouter);
        BtnFamille=findViewById(R.id.btnFamille);
        BtnFinance=findViewById(R.id.btnFinance);
        BtnEcole=findViewById(R.id.btnEcole);
        BtnPolitique=findViewById(R.id.btnPolitique);
        BtnBoulot=findViewById(R.id.btnBoulot);

        BtnFamille.setOnClickListener(v -> {
            selectedTagName = "Famille";
            selectedTagColor = "#D9D9D9";
        });
        BtnBoulot.setOnClickListener(v -> {
            selectedTagName = "Boulot";
            selectedTagColor = "#D9D9D9";
        });
        BtnEcole.setOnClickListener(v -> {
            selectedTagName = "Ecole";
            selectedTagColor = "#D9D9D9";
        });
        BtnFinance.setOnClickListener(v -> {
            selectedTagName = "Finance";
            selectedTagColor = "#D9D9D9";
        });
        BtnPolitique.setOnClickListener(v -> {
            selectedTagName = "Politique";
            selectedTagColor = "#D9D9D9";
        });
        BtnAjouter.setOnClickListener(v -> {
            String titreSaisi = titre.getText().toString().trim();
            String descriptionSaisi = desciption.getText().toString().trim();

            if (titreSaisi.isEmpty()) {
                Toast.makeText(this, "Le titre est obligatoire", Toast.LENGTH_SHORT).show();
                return;
            }

            // On prépare le retour vers Item_note
            Intent returnIntent = new Intent();
            returnIntent.putExtra("NOTE_TITLE", titreSaisi);
            returnIntent.putExtra("NOTE_DESC", descriptionSaisi);
            returnIntent.putExtra("NOTE_TAG_NAME", selectedTagName);
            returnIntent.putExtra("NOTE_TAG_COLOR", selectedTagColor);

            setResult(RESULT_OK, returnIntent);
            finish(); // Ferme AjoutNote et retourne à MyTask
        });

        BtnClose=findViewById(R.id.btn_close);
        BtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AjoutNote.this, Home_empty.class);
                startActivity(i);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Méthode pour ajouter le tag dynamiquement
    private void addNewTagToScreen(String name, String colorHex) {
        LinearLayout container = findViewById(R.id.dynamic_tags_container);
        MaterialButton newTagButton = new MaterialButton(this);

        newTagButton.setText(name);
        newTagButton.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 12);
        newTagButton.setAllCaps(false);
        newTagButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorHex)));

        // ⚠️ C'EST ICI QU'ON APPLIQUE LA COULEUR DE TEXTE ⚠️
        newTagButton.setTextColor(getTextColorForBackground(colorHex));

        float density = getResources().getDisplayMetrics().density;
        int radiusPx = (int) (20 * density);
        int marginEndPx = (int) (8 * density);

        newTagButton.setCornerRadius(radiusPx);
        newTagButton.setInsetTop(0);
        newTagButton.setInsetBottom(0);
        newTagButton.setMinHeight(0);
        newTagButton.setMinWidth(0);
        newTagButton.setPadding(0, 0, 0, 0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, marginEndPx, 0);
        newTagButton.setLayoutParams(params);

        newTagButton.setOnClickListener(v -> {
            // Quand on clique sur le tag dynamique, on met à jour la sélection
            selectedTagName = name;
            selectedTagColor = colorHex;

            // Optionnel : Un petit message pour confirmer la sélection
            Toast.makeText(AjoutNote.this, "Tag " + name + " sélectionné", Toast.LENGTH_SHORT).show();
        });
        container.addView(newTagButton);
    }

    public static int getTextColorForBackground(String colorHex) {
        int color = Color.parseColor(colorHex);
        // Calcul de la luminosité (formule standard)
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        double luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255;

        // Si c'est clair, on met du noir. Si c'est sombre, on met du blanc.
        if (luminance > 0.5) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }
}