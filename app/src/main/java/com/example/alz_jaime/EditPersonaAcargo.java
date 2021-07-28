package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditPersonaAcargo extends AppCompatActivity {

    private DatabaseReference cargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_persona_acargo);

        cargo = FirebaseDatabase.getInstance().getReference();

        EditText nombre = (EditText) findViewById(R.id.CuidadorNombre);
        EditText direccion = (EditText) findViewById(R.id.CuidadorDireccion);
        EditText telefono = (EditText) findViewById(R.id.CuidadorTelefono);

        Button Save = (Button) findViewById(R.id.GCargo);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MAIL = DatosUsuario.getCorreo();
                String cad = MAIL.replace(".", "");

                String Nombre = nombre.getText().toString();
                String Direccion = direccion.getText().toString();
                String Telefono = telefono.getText().toString();

                if(!Nombre.equals("") && !Direccion.equals("") && !Telefono.equals("")){

                    DatosCuidadores.setCuidador(Nombre,Direccion,Telefono);

                    Map<String, Object> personaMap = new HashMap<>();
                    personaMap.put("nombre", DatosCuidadores.getNombre() );
                    personaMap.put("direccion", DatosCuidadores.getDireccion() );
                    personaMap.put("telefono", DatosCuidadores.getTelefono() );

                    cargo.child("Usuarios").child(cad).child("DatosCuidadores").updateChildren(personaMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent intent = new Intent (v.getContext(), Principal.class);
                            startActivityForResult(intent, 0);
                            Toast correcto = Toast.makeText(getApplicationContext(), "Se han actualizado los datos", Toast.LENGTH_SHORT);
                            correcto.show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast Error = Toast.makeText(getApplicationContext(), "Ocurrio un error, no se actualizaron los datos", Toast.LENGTH_SHORT);
                            Error.show();
                        }
                    });

                }
                else{
                    Toast error = Toast.makeText(getApplicationContext(), "No se pueden dejar campos vacios", Toast.LENGTH_SHORT);
                    error.show();
                }

                Intent intent = new Intent (v.getContext(), PersonasAcargo.class);
                startActivityForResult(intent, 0);

                Toast Guardado = Toast.makeText(getApplicationContext(), "Se ah actualizado el contacto", Toast.LENGTH_SHORT);
                Guardado.show();
            }
        });
    }
}