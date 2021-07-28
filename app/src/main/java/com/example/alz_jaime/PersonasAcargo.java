package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PersonasAcargo extends AppCompatActivity {

    private DatabaseReference DeleteCuidador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas_acargo);

        DeleteCuidador = FirebaseDatabase.getInstance().getReference();

        TextView nom = (TextView) findViewById( R.id.OnNomCui );
        TextView dir = (TextView) findViewById( R.id.OnDirCui );
        TextView tel = (TextView) findViewById( R.id.OnTelCui );

        nom.setText( DatosCuidadores.getNombre() );
        dir.setText( DatosCuidadores.getDireccion() );
        tel.setText( DatosCuidadores.getTelefono() );

        ImageView regresar = (ImageView) findViewById(R.id.CargoRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Principal.class);
                startActivityForResult(intent, 0);
            }
        });

        Button actualizar = (Button) findViewById(R.id.editarCuidador);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), EditPersonaAcargo.class);
                startActivityForResult(intent, 0);
            }
        });
        Button borrar = (Button) findViewById(R.id.BorrarCuidador);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MAIL = DatosUsuario.getCorreo();
                String cad = MAIL.replace(".","");

                Map<String, Object> personaMap = new HashMap<>();
                personaMap.put("nombre", "--Nombre--" );
                personaMap.put("direccion", "--Direccion--" );
                personaMap.put("telefono", "--Telefono--" );

                DeleteCuidador.child("Usuarios").child(cad).child("DatosCuidadores").updateChildren(personaMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        nom.setText( "--Nombre--" );
                        dir.setText( "--Direccion--" );
                        tel.setText( "--Telefono--" );

                        DatosCuidadores.setCuidador("--Nombre--", "--Direccion--", "--Telefono--");

                        Toast correcto = Toast.makeText(getApplicationContext(), "Se han Borrado los datos del cuidador", Toast.LENGTH_SHORT);
                        correcto.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast Error = Toast.makeText(getApplicationContext(), "Ocurrio un error al borrar la paersona", Toast.LENGTH_SHORT);
                        Error.show();
                    }
                });
            }
        });
    }
}