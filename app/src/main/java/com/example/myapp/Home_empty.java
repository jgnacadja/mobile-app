package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home_empty extends AppCompatActivity {

    private TextView recupmail;
    private Button btnAdd;

    // Variables pour gérer l'affichage des notes
    private LinearLayout notesContainer;
    private LinearLayout emptyStateLayout;
    private View scrollNotes;
    private ActivityResultLauncher<Intent> noteLauncher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_empty);

        // --- 1. INITIALISATION DES VUES ---
        recupmail = findViewById(R.id.recup_email);
        btnAdd = findViewById(R.id.bntAdd);

        notesContainer = findViewById(R.id.notes_container);
        emptyStateLayout = findViewById(R.id.empty_state_layout);
        scrollNotes = findViewById(R.id.scroll_notes);

        // --- 2. RÉCUPÉRATION DE L'EMAIL ---
        Intent intent = getIntent();
        String emailRecu = intent.getStringExtra("EMAIL_UTILISATEUR");
        if (emailRecu != null) {
            recupmail.setText(emailRecu);
        }

        // --- 3. PRÉPARATION DU RÉCEPTEUR DE NOTES ---
        noteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String titre = data.getStringExtra("NOTE_TITLE");
                        String desc = data.getStringExtra("NOTE_DESC");
                        String tagName = data.getStringExtra("NOTE_TAG_NAME");
                        String tagColor = data.getStringExtra("NOTE_TAG_COLOR");

                        // On ajoute la note visuellement
                        ajouterNoteVisuelle(titre, desc, tagName, tagColor);
                    }
                }
        );

        // --- 4. BOUTON AJOUTER ---
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_empty.this, AjoutNote.class);
                noteLauncher.launch(intent);
            }
        });

        // --- 5. GESTION DES BORDURES ---
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // --- 6. MÉTHODE POUR CRÉER LA CARTE ---
    private void ajouterNoteVisuelle(String titre, String desc, String tagName, String tagColor) {
        // On cache le message "Bonne journée" et on affiche la liste
        emptyStateLayout.setVisibility(View.GONE);
        scrollNotes.setVisibility(View.VISIBLE);

        // ⚠️ VÉRIFIE BIEN LE NOM DE TON FICHIER ICI (item_note ou activity_item_note) ⚠️
        View noteView = LayoutInflater.from(this).inflate(R.layout.item_note, notesContainer, false);

        // On récupère les éléments de la carte
        TextView tvTitle = noteView.findViewById(R.id.tv_note_title);
        TextView tvDesc = noteView.findViewById(R.id.tv_note_desc);
        TextView tvTag = noteView.findViewById(R.id.tv_note_tag);

        // On remplit avec les données
        tvTitle.setText(titre);
        tvDesc.setText(desc);
        tvTag.setText(tagName);

        /// 1. La couleur de fond
        tvTag.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(tagColor)));
        // 2. La couleur du texte (blanc ou noir selon la couleur de fond)
        tvTag.setTextColor(getTextColorForBackground(tagColor));

        // On ajoute la carte au conteneur
        notesContainer.addView(noteView);
    }
    // Méthode pour déterminer si le texte doit être noir ou blanc
    public static int getTextColorForBackground(String colorHex) {
        int color = Color.parseColor(colorHex);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        double luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255;

        if (luminance > 0.5) {
            return Color.BLACK; // Couleur claire -> Texte noir
        } else {
            return Color.WHITE; // Couleur sombre -> Texte blanc
        }
    }
}