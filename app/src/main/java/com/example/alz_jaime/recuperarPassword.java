package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;

public class recuperarPassword extends AppCompatActivity {

    private DatabaseReference Alz;

    String CorreoApp = "AlzJaimeApp@gmail.com";
    String ContrasenaApp = "Carlosp1";
    String Mensaje;
    String CorreoUsuario;

    String email;
    String apellidos;
    String contraseña;
    String edad;
    String historial;
    String nombres;
    String padecimiento;
    String telefono;

    //Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        Alz = FirebaseDatabase.getInstance().getReference();
        EditText correo = (EditText) findViewById(R.id.RecuperarCorreo);

        Button sendcode = (Button) findViewById(R.id.SendCode);
        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MAIL = correo.getText().toString();
                String cad = MAIL.replace(".", "");

                if( !MAIL.equals("") ){
                    Alz.child("Usuarios").child(cad).child("DatosPersonales").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                email = snapshot.child("email").getValue().toString();
                                apellidos = snapshot.child("apellidos").getValue().toString();
                                contraseña = snapshot.child("contrasena").getValue().toString();
                                edad = snapshot.child("edad").getValue().toString();
                                historial = snapshot.child("historial").getValue().toString();
                                nombres = snapshot.child("nombres").getValue().toString();
                                padecimiento = snapshot.child("padecimiento").getValue().toString();
                                telefono = snapshot.child("telefono").getValue().toString();

                                DatosUsuario.setCorreo(MAIL);

                                DatosUsuario.setDatos(nombres,apellidos,telefono,edad,padecimiento,email,contraseña,historial);

                            }
                            String mensaje = GenerarMensaje();

                            Intent intent = new Intent (v.getContext(), codigoPassword.class);
                            startActivityForResult(intent, 0);
                            Toast Error = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
                            Error.show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast Error = Toast.makeText(getApplicationContext(), "El correo no esta registrado", Toast.LENGTH_SHORT);
                            Error.show();
                        }
                    });
                }
                else{
                    Toast Error = Toast.makeText(getApplicationContext(), "Los campos no pueden quedar vacios", Toast.LENGTH_SHORT);
                    Error.show();
                }
            }
        });
    }
    public static String GenerarMensaje(){
        GenerarCodigo.Generar();

        String Message = "Tu codigo de recuperacion de cuenta es: \n\n";
        Message += GenerarCodigo.getCode();

        return Message;
    }
}