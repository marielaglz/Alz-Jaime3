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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class newPassword extends AppCompatActivity {

    private DatabaseReference ActualizarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        ActualizarPassword = FirebaseDatabase.getInstance().getReference();

        EditText np = (EditText) findViewById(R.id.NewPass);
        EditText npc = (EditText) findViewById(R.id.NewPassConfirm);

        Button save = (Button) findViewById(R.id.SavePassword);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NP = np.getText().toString();
                String NPC = npc.getText().toString();

                if(!NP.equals("") && !NPC.equals("")){
                    if( NP.equals(NPC) ){
                        String MAIL = DatosUsuario.getCorreo();
                        String cad = MAIL.replace(".","");

                        Map<String, Object> personaMap = new HashMap<>();
                        personaMap.put("nombres", DatosUsuario.getNombre() );
                        personaMap.put("apellidos", DatosUsuario.getApellidos() );
                        personaMap.put("telefono", DatosUsuario.getTelefono() );
                        personaMap.put("edad", DatosUsuario.getEdad() );
                        personaMap.put("disgnodtico", DatosUsuario.getDiagnostico() );
                        personaMap.put("email", DatosUsuario.getCorreo() );
                        personaMap.put("contrasena", NP );
                        personaMap.put("historial", RegistroMedicamentoTomado.getRegistro() );

                        ActualizarPassword.child("Usuarios").child(cad).child("DatosPersonales").updateChildren(personaMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                DatosUsuario.setContraseña(NP);

                                Intent intent = new Intent (v.getContext(), Login.class);
                                startActivityForResult(intent, 0);

                                Toast correcto = Toast.makeText(getApplicationContext(), "Se ah actualizado la contraseña correctamente", Toast.LENGTH_SHORT);
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
                        Toast error = Toast.makeText(getApplicationContext(), "las contraseñas deben ser iguales", Toast.LENGTH_SHORT);
                        error.show();
                    }
                }
                else{
                    Toast error = Toast.makeText(getApplicationContext(), "Los campos no pueden quedar vacios", Toast.LENGTH_SHORT);
                    error.show();
                }
            }
        });
    }
}