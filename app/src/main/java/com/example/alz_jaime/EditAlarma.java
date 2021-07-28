package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alz_jaime.ui.slideshow.SlideshowFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditAlarma extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    int horas;
    int minutos;

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        TextView hour = (TextView) findViewById(R.id.Hora);

        horas = hourOfDay;
        minutos = minute;

        if( hourOfDay < 12 ){
            if (hourOfDay <= 9){
                if (hourOfDay == 0){
                    if(minute <= 9 )
                        hour.setText("12" + " : " + "0" + minute + " AM");
                    else
                        hour.setText("12" + " : " + minute + " AM");
                }
                else{
                    if(minute <= 9 )
                        hour.setText("0" + hourOfDay + " : " + "0" +minute + " AM");
                    else
                        hour.setText("0" + hourOfDay + " : " + minute + " AM");
                }
            }
            else{
                if(minute <= 9 )
                    hour.setText(hourOfDay + " : " + "0" +minute + " AM");
                else
                    hour.setText(hourOfDay + " : " + minute + " AM");
            }
        }
        else{
            if (hourOfDay <= 9){
                if(minute <= 9 )
                    hour.setText("0" + (hourOfDay - 12) + " : " + "0" +minute + " PM");
                else
                    hour.setText("0" + (hourOfDay - 12) + " : " + minute + " PM");
            }
            else{
                if (hourOfDay == 12){
                    if(minute <= 9 )
                        hour.setText("12" + " : " + "0" + minute + " PM");
                    else
                        hour.setText("12" + " : " + minute + " PM");
                }
                else{
                    if(minute <= 9 )
                        hour.setText( (hourOfDay - 12) + " : " + "0" +minute + " PM");
                    else
                        hour.setText( (hourOfDay - 12) + " : " + minute + " PM");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarma);

        DialogFragment time = new TimePicker();
        time.show(getSupportFragmentManager(), "Prueba");

        Button change = (Button) findViewById(R.id.CambiarHora);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment time = new TimePicker();
                time.show(getSupportFragmentManager(), "Prueba");
            }
        });

        Button Cancel = (Button) findViewById(R.id.CancelAlarma);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Principal.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageView regresar = (ImageView) findViewById(R.id.ReturnAlarma);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Principal.class);
                startActivityForResult(intent, 0);
            }
        });


        Button Create = (Button) findViewById(R.id.CreateAlarm);
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView hour = (TextView) findViewById(R.id.Hora);
                String hora = hour.getText().toString();
                EditText nombremedi = (EditText) findViewById(R.id.nomMedi);
                String medi = nombremedi.getText().toString();

                if(!medi.equals("") ){
                    DatosAlarmas.setNommedi(medi);
                    DatosAlarmas.setHora(horas);
                    DatosAlarmas.setMin(minutos);

                    Intent intent = new Intent (v.getContext(), DayAlarm.class);
                    startActivityForResult(intent, 0);
                }
                else{
                    Toast error = Toast.makeText(getApplicationContext(), "Ingresa el nombre del medicamento", Toast.LENGTH_SHORT);
                    error.show();
                }
            }
        });
    }
}