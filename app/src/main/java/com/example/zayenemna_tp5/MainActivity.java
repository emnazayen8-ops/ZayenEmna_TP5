package com.example.zayenemna_tp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtPrenom, edtPoids, edtTaille;
    private Button btnCalculer;
    private RadioButton rbHomme, rbFemme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrenom = findViewById(R.id.etPrenom);
        edtPoids = findViewById(R.id.etPoids);
        edtTaille = findViewById(R.id.etTaille);
        btnCalculer = findViewById(R.id.btnCalculer);
        rbHomme = findViewById(R.id.rbHomme);
        rbFemme = findViewById(R.id.rbFemme);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String prenom = edtPrenom.getText().toString().trim();
                String poidsStr = edtPoids.getText().toString().trim();
                String tailleStr = edtTaille.getText().toString().trim();

                // Vérification des champs vides (évite le crash)
                if (prenom.isEmpty() || poidsStr.isEmpty() || tailleStr.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir tous les champs",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                float poids = Float.parseFloat(poidsStr);
                float taille = Float.parseFloat(tailleStr);   // en mètres (ex : 1.70)

                float imc = poids / (taille * taille);

                String sexe = rbHomme.isChecked() ? "Homme" : "Femme";

                Intent i = new Intent(MainActivity.this, ResultatActivity.class);
                i.putExtra("prenom", prenom);
                i.putExtra("imc", (double) imc);
                i.putExtra("sexe", sexe);
                startActivity(i);
            }
        });
    }
}