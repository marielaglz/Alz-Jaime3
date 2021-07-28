package com.example.alz_jaime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class codigoPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_password);

        EditText Codigo1 = (EditText) findViewById(R.id.Cod1);
        EditText Codigo2 = (EditText) findViewById(R.id.Cod2);
        EditText Codigo3 = (EditText) findViewById(R.id.Cod3);
        EditText Codigo4 = (EditText) findViewById(R.id.Cod4);
        EditText Codigo5 = (EditText) findViewById(R.id.Cod5);

        //Codigo confirmado
        Button confirmcode = (Button) findViewById(R.id.ConfirmCode);
        confirmcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = GenerarCodigo.getCodeComprobacion();

                String code = Codigo1.getText().toString();
                code += Codigo2.getText().toString();
                code += Codigo3.getText().toString();
                code += Codigo4.getText().toString();
                code += Codigo5.getText().toString();

                if( code.equals(codigo) ){
                    Intent intent = new Intent (v.getContext(), newPassword.class);
                    startActivityForResult(intent, 0);
                }
                else {
                    Codigo1.setText("");
                    Codigo2.setText("");
                    Codigo3.setText("");
                    Codigo4.setText("");
                    Codigo5.setText("");
                    Toast error = Toast.makeText(getApplicationContext(), "Codigo Incorrecto", Toast.LENGTH_SHORT);
                    error.show();
                }
            }
        });
        // Reenviar codigo
        TextView resendcode = (TextView) findViewById(R.id.ReSendCode);
        resendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}