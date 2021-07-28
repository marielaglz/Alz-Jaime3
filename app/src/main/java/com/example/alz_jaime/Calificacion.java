package com.example.alz_jaime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Calificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

        Button calificar = (Button) findViewById(R.id.Calificar);

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent (v.getContext(), Principal.class);
                startActivityForResult(inten, 0);
                Toast calificado = Toast.makeText(getApplicationContext(), "Gracis por calificar la aplicacion", Toast.LENGTH_SHORT);
                calificado.show();

            }
        });
    }
}