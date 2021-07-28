package com.example.alz_jaime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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

public class DayAlarm extends AppCompatActivity {

    private DatabaseReference ActualizarHistorial;
    String f;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_alarm);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy");
        f = df.format( c.getTime() );

        ActualizarHistorial = FirebaseDatabase.getInstance().getReference();

        Button Cancel = (Button) findViewById(R.id.FinAlarm);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(), Principal.class);
                startActivityForResult(intent, 0);

                Toast mensaje = Toast.makeText(getApplicationContext(), "No se creo la alarma", Toast.LENGTH_SHORT);
                mensaje.show();
            }
        });

        Button aceptar = (Button) findViewById(R.id.AddAlarm);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombre = DatosAlarmas.getNommedi();
                String histo = DatosUsuario.getHistorial();

                RegistroMedicamentoTomado.setFullregistro(histo);
                RegistroMedicamentoTomado.setRegistro(f, nombre);

                histo = RegistroMedicamentoTomado.fullregistro();
                DatosUsuario.setHistorial(histo);

                String MAIL = DatosUsuario.getCorreo();
                String cad = MAIL.replace(".","");

                Map<String, Object> personaMap = new HashMap<>();
                personaMap.put("nombres", DatosUsuario.getNombre() );
                personaMap.put("apellidos", DatosUsuario.getApellidos() );
                personaMap.put("telefono", DatosUsuario.getTelefono() );
                personaMap.put("edad", DatosUsuario.getEdad() );
                personaMap.put("disgnodtico", DatosUsuario.getDiagnostico() );
                personaMap.put("email", DatosUsuario.getCorreo() );
                personaMap.put("contrasena", DatosUsuario.getContraseña() );
                personaMap.put("historial", RegistroMedicamentoTomado.getRegistro() );

                ActualizarHistorial.child("Usuarios").child(cad).child("DatosPersonales").updateChildren(personaMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        String mensaje = "Hora de tomar tu medicamento\n" + DatosAlarmas.getNommedi();
                        establecerAlarma(mensaje, DatosAlarmas.getHora(), DatosAlarmas.getMin());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast Error = Toast.makeText(getApplicationContext(), "Ocurrio un error, no se actualizaron los datos", Toast.LENGTH_SHORT);
                        Error.show();
                    }
                });
            }
        });

        CalendarView calen = (CalendarView) findViewById(R.id.calendarH);
        calen.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                f = dayOfMonth + "/" + month + "/" + year;
            }
        });
    }
    public void establecerAlarma(String mensaje, int hora, int minutos){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hora);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutos);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, mensaje);

        if(intent.resolveActivity(getPackageManager() ) != null){
            Toast exito = Toast.makeText(getApplicationContext(), "Se ah creado la alarma", Toast.LENGTH_SHORT);
            exito.show();
            startActivity(intent);
        }
        else{
            Toast error = Toast.makeText(getApplicationContext(), "Ocurrio un error intentalo mas tarde", Toast.LENGTH_SHORT);
            error.show();
        }
    }
}