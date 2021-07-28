package com.example.alz_jaime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Alarmas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas);

        TextView DA = (TextView) findViewById(R.id.DiaAlarmas);
        DA.setText( DatosAlarmas.getFullRegistro() );

        Button Actualizar = (Button) findViewById(R.id.EDITALARMA);
        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), EditAlarma.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}