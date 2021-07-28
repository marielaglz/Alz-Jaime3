package com.example.alz_jaime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrarse extends AppCompatActivity {

    private DatabaseReference Alz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        Alz = FirebaseDatabase.getInstance().getReference();

        EditText nombre = (EditText) findViewById(R.id.RegNom);
        EditText apellidos = (EditText) findViewById(R.id.RegApe);
        EditText correo = (EditText) findViewById(R.id.RegEmail);
        EditText contrasena = (EditText) findViewById(R.id.RegPass);
        EditText telefono = (EditText) findViewById(R.id.RegTel);
        EditText edad = (EditText) findViewById(R.id.RegAge);
        EditText padecimiento = (EditText) findViewById(R.id.RegPadecimiento);
        String Historial = "Historial de medicamento";

        String N = "Nombre";
        String D = "Direccion";
        String T = "Telefono";

        //Registrarse
        Button Registro = (Button) findViewById(R.id.registrado);
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = nombre.getText().toString();
                String Apellidos = apellidos.getText().toString();
                String Email = correo.getText().toString();
                String Telefono = telefono.getText().toString();
                String Edad = edad.getText().toString();
                String Contrasena = contrasena.getText().toString();
                String Padecimiento = padecimiento.getText().toString();

                if(!Nombre.equals("") && !Apellidos.equals("") && !Email.equals("") && !Telefono.equals("") &&
                        !Edad.equals("") && !Contrasena.equals("") && !Padecimiento.equals("")) {

                    String E_mail = Email.replace(".","");

                    DBUsuarios User = new DBUsuarios(Nombre, Apellidos, Telefono, Edad, Email, Contrasena, Padecimiento,Historial);
                    BDCuidadores Cuidadores = new BDCuidadores(N ,D, T);

                    Alz.child("Usuarios").child(E_mail).child("DatosPersonales").setValue(User);
                    Alz.child("Usuarios").child(E_mail).child("DatosCuidadores").setValue(Cuidadores);

                    DatosUsuario.setDatos(Nombre, Apellidos, Telefono, Edad, Padecimiento, Email, Contrasena,Historial);

                    // Mandar mensaje de que se ah registrado correcatamente
                    AlertDialog.Builder Confirmar = new AlertDialog.Builder(Registrarse.this);
                    Confirmar.setMessage("Sus datos se han registrado con exito")
                            .setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Cambiar a la pantalla de login
                            Intent intent = new Intent (v.getContext(), Login.class);
                            startActivityForResult(intent, 0);
                            //Cerrar el dialogo
                            dialog.cancel();
                        }
                    });
                    Confirmar.show();
                }
                else{
                    Toast Error = Toast.makeText(getApplicationContext(), "Se deben llenar todos los campor para registrarse Datos", Toast.LENGTH_SHORT);
                    Error.show();
                }
            }
        });
    }
}