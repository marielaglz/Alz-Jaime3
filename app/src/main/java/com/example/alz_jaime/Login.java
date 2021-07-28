package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPromt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder Confirmar = new AlertDialog.Builder(this);
        Confirmar.setMessage("¿Desea salir de la aplicacion?")
                .setCancelable(false).setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            // Si Compra el pedido
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setNegativeButton("NO",new DialogInterface.OnClickListener() {
            @Override
            // Si Compra el pedido
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Confirmar.show();

        return super.onKeyDown(keyCode, event);
    }

    private DatabaseReference Alz;

    //Datos Usuarios
    String email = "";
    String apellidos = "";
    String contraseña = "";
    String edad = "";
    String historial = "";
    String nombres = "";
    String padecimiento = "";
    String telefono = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Alz = FirebaseDatabase.getInstance().getReference();

        EditText EMAIL = (EditText) findViewById(R.id.LogEmail);
        EditText PASS = (EditText) findViewById(R.id.LogPass);

        //Iniciar Sesion
        Button Iniciar = (Button) findViewById(R.id.IniSes);
        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = PASS.getText().toString();
                String MAIL = EMAIL.getText().toString();
                String cad = MAIL.replace(".", "");

                if (!MAIL.equals("")) {
                    Alz.child("Usuarios").child(cad).child("DatosPersonales").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                email = snapshot.child("email").getValue().toString();
                                apellidos = snapshot.child("apellidos").getValue().toString();
                                contraseña = snapshot.child("contrasena").getValue().toString();
                                edad = snapshot.child("edad").getValue().toString();
                                historial = snapshot.child("historial").getValue().toString();
                                nombres = snapshot.child("nombres").getValue().toString();
                                padecimiento = snapshot.child("padecimiento").getValue().toString();
                                telefono = snapshot.child("telefono").getValue().toString();

                                if (password.equals(contraseña)) {
                                    DatosUsuario.setDatos(nombres, apellidos, telefono, edad, padecimiento, email, contraseña, historial);

                                    Intent intent = new Intent(v.getContext(), Principal.class);
                                    startActivityForResult(intent, 0);
                                    Toast welcome = Toast.makeText(getApplicationContext(), "Bienvenido " + nombres, Toast.LENGTH_LONG);
                                    welcome.show();
                                } else {
                                    Toast error = Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG);
                                    error.show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast Error = Toast.makeText(getApplicationContext(), "El correo no esta registrado", Toast.LENGTH_SHORT);
                            Error.show();
                        }
                    });
                } else {
                    Toast Error = Toast.makeText(getApplicationContext(), "Los campos no pueden quedar vacios", Toast.LENGTH_SHORT);
                    Error.show();
                }
            }
        });
        //Registrarse
        Button Registro = (Button) findViewById(R.id.Reg);
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registrarse.class);
                startActivityForResult(intent, 0);
            }
        });
        //Recuperar contraseña
        TextView Recuperar = (TextView) findViewById(R.id.REC);
        Recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), recuperarPassword.class);
                startActivityForResult(intent, 0);
            }
        });

        executor = ContextCompat.getMainExecutor(this);
        biometricPromt = new BiometricPrompt(Login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast Error = Toast.makeText(getApplicationContext(), "Ocurrio un error durante la autenticacion\nSus datos no se guardaran", Toast.LENGTH_SHORT);
                Error.show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast exito = Toast.makeText(getApplicationContext(), "La autenticacion se realizo correctamente\nSus datos no se guardaran", Toast.LENGTH_SHORT);
                exito.show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast fallo = Toast.makeText(getApplicationContext(), "Ocurrio un fallo durante la autenticacion\nSus datos no se guardaran", Toast.LENGTH_SHORT);
                fallo.show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Registro")
                .setNegativeButtonText("Registrate con correo")
                .build();

        //Iniciar sesion con Google
        Button iniciarBio = (Button) findViewById(R.id.IniBio);
        iniciarBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPromt.authenticate(promptInfo);

            }
        });
    }
}