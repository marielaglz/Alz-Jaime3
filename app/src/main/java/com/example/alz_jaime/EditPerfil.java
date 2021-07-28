package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditPerfil extends AppCompatActivity {

    private DatabaseReference Alz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);

        Alz = FirebaseDatabase.getInstance().getReference();

        EditText nombre = (EditText) findViewById(R.id.NomUser);
        EditText apellidos = (EditText) findViewById(R.id.ApeUser);
        EditText telefono = (EditText) findViewById(R.id.TelUser);
        EditText edad = (EditText) findViewById(R.id.AgeUser);
        EditText diagnostico = (EditText) findViewById(R.id.DiagnosticoUser);

        nombre.setText( DatosUsuario.getNombre() );
        apellidos.setText( DatosUsuario.getApellidos() );
        edad.setText( DatosUsuario.getEdad() );
        telefono.setText( DatosUsuario.getTelefono() );
        diagnostico.setText( DatosUsuario.getDiagnostico() );

        Button Save = (Button) findViewById(R.id.SaveChanges);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String lastname = apellidos.getText().toString();
                String Diag = diagnostico.getText().toString();
                String age = edad.getText().toString();
                String phone = telefono.getText().toString();

                String mail = DatosUsuario.getCorreo();
                String pass = DatosUsuario.getContraseña();
                String histo = DatosUsuario.getHistorial();

                DatosUsuario.setDatos(name,lastname,phone,age,Diag, mail, pass, histo);
                if( !nombre.equals("") && !apellidos.equals("") && !telefono.equals("") && !edad.equals("") && !diagnostico.equals("") ) {
                    String cad = mail.replace(".","");

                    Map<String, Object> personaMap = new HashMap<>();
                    personaMap.put("nombres", nombre.getText().toString() );
                    personaMap.put("apellidos", apellidos.getText().toString() );
                    personaMap.put("telefono", telefono.getText().toString() );
                    personaMap.put("edad", edad.getText().toString() );
                    personaMap.put("disgnodtico", diagnostico.getText().toString() );
                    personaMap.put("email", mail );
                    personaMap.put("contrasena", pass );
                    personaMap.put("historial", histo );

                    Alz.child("Usuarios").child(cad).child("DatosPersonales").updateChildren(personaMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            DatosUsuario.setDatos(name, lastname, phone, age, Diag, mail, pass, histo);

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
                    Toast Error = Toast.makeText(getApplicationContext(), "No se pueden dejar campos vacios", Toast.LENGTH_SHORT);
                    Error.show();
                }
            }
        });

        Button cancel = (Button) findViewById(R.id.CancelChanges);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Principal.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}