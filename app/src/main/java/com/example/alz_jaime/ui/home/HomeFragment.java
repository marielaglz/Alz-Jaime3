package com.example.alz_jaime.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.alz_jaime.Alarmas;
import com.example.alz_jaime.Contacto;
import com.example.alz_jaime.DatosAlarmas;
import com.example.alz_jaime.DatosCuidadores;
import com.example.alz_jaime.DatosUsuario;
import com.example.alz_jaime.InfCont;
import com.example.alz_jaime.Llamada;
import com.example.alz_jaime.Login;
import com.example.alz_jaime.M;
import com.example.alz_jaime.PersonasAcargo;
import com.example.alz_jaime.Principal;
import com.example.alz_jaime.R;
import com.example.alz_jaime.RegistroMedicamentoTomado;
import com.example.alz_jaime.RegistroMedico;
import com.example.alz_jaime.databinding.FragmentHomeBinding;
import com.example.alz_jaime.ui.slideshow.SlideshowFragment;
import com.example.alz_jaime.ui.slideshow.SlideshowViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private DatabaseReference Alarms;
    private DatabaseReference cuidadores;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView Emergency = (ImageView) root.findViewById(R.id.EmerBtn);
        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = DatosCuidadores.getTelefono();
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:" + phone));
                startActivity(intent2);
            }
        });
        ImageView Registro = (ImageView) root.findViewById(R.id.Cale);
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), RegistroMedico.class);
                startActivityForResult(intent, 0);
            }
        });
        ImageView alarma = (ImageView) root.findViewById(R.id.ALARM);
        alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Alarmas.class);
                startActivityForResult(intent, 0);
            }
        });

        cuidadores = FirebaseDatabase.getInstance().getReference();

        ImageView datcuidador = (ImageView) root.findViewById(R.id.Cui);
        datcuidador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MAIL = DatosUsuario.getCorreo();
                String cad = MAIL.replace(".","");

                cuidadores.child("Usuarios").child(cad).child("DatosCuidadores").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            String direccion = snapshot.child("direccion").getValue().toString();
                            String nombre = snapshot.child("nombre").getValue().toString();
                            String telefono = snapshot.child("telefono").getValue().toString();

                            DatosCuidadores.setCuidador(nombre,direccion,telefono);

                            Intent intent = new Intent (v.getContext(), PersonasAcargo.class);
                            startActivityForResult(intent, 0);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}