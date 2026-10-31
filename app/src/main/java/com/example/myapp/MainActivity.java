package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnOk;
    private TextView txtError;
    private TextView txtEmailLabel;
    private TextView txtWelcome;
    private TextView txtEmailSuccess;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Formulaire
        edtEmail = findViewById(R.id.edtEmail);
        btnOk = findViewById(R.id.btnOk);
        txtError = findViewById(R.id.txtError);
        txtEmailLabel = findViewById(R.id.txtEmailLabel);

        // Succès
        txtWelcome = findViewById(R.id.txtWelcome);
        txtEmailSuccess = findViewById(R.id.txtEmailSuccess);
        progressBar = findViewById(R.id.progressBar);

        txtError.setVisibility(View.GONE);

        btnOk.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {


                txtEmailLabel.setVisibility(View.GONE);
                edtEmail.setVisibility(View.GONE);
                txtError.setVisibility(View.GONE);
                btnOk.setVisibility(View.GONE);


                txtWelcome.setVisibility(View.VISIBLE);
                txtEmailSuccess.setText(email);
                txtEmailSuccess.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);


                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();

                }, 3000);

            } else {
                txtError.setText("Veuillez fournir un email valide");
                txtError.setVisibility(View.VISIBLE);
            }
        });

        edtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                txtError.setVisibility(View.GONE);
            }
        });
    }
}