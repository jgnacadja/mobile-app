package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnOk;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        edtEmail = findViewById(R.id.edtEmail);
        btnOk = findViewById(R.id.btnOk);
        txtError = findViewById(R.id.txtError);


        txtError.setVisibility(View.GONE);

        btnOk.setOnClickListener(v -> {

            String email = edtEmail.getText().toString().trim();


            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {


                txtError.setVisibility(View.GONE);

            } else {


                txtError.setText("Email invalide");
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
