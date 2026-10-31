package com.example.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity4 extends AppCompatActivity {

    private int selectedColor = Color.parseColor("#E91E63");
    private View selectedCircle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ImageView btnBack = findViewById(R.id.btnBack);
        MaterialButton btnEnregistre = findViewById(R.id.btnEnregistre);
        TextInputEditText edtLabel = findViewById(R.id.edtLabel);

        btnBack.setOnClickListener(v -> finish());

        setupColorCircles();

        btnEnregistre.setOnClickListener(v -> {
            String label = edtLabel.getText().toString().trim();

            if (label.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un nom de tag", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("tag_name", label);
            resultIntent.putExtra("tag_color", selectedColor);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void setupColorCircles() {
        int[] circleIds = {
                R.id.circle1, R.id.circle2, R.id.circle3, R.id.circle4, R.id.circle5,
                R.id.circle6, R.id.circle7, R.id.circle8, R.id.circle9, R.id.circle10
        };

        int[] colors = {
                Color.parseColor("#E91E63"),
                Color.parseColor("#CE93D8"),
                Color.parseColor("#7B1FA2"),
                Color.parseColor("#212121"),
                Color.parseColor("#FF9800"),
                Color.parseColor("#009688"),
                Color.parseColor("#00BCD4"),
                Color.parseColor("#795548"),
                Color.parseColor("#F44336"),
                Color.parseColor("#3F51B5")
        };

        for (int i = 0; i < circleIds.length; i++) {
            View circle = findViewById(circleIds[i]);
            final int color = colors[i];

            circle.setOnClickListener(v -> {
                selectedColor = color;
                selectedCircle = circle;

            });
        }
    }
}