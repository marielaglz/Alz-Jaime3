package com.example.alz_jaime.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.alz_jaime.DatosUsuario;
import com.example.alz_jaime.EditPerfil;
import com.example.alz_jaime.Llamada;
import com.example.alz_jaime.Principal;
import com.example.alz_jaime.R;
import com.example.alz_jaime.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView nombre = (TextView) root.findViewById(R.id.PerNom);
        TextView apellidos = (TextView) root.findViewById(R.id.PerApe);
        TextView telefono = (TextView) root.findViewById(R.id.PerTel);
        TextView edad = (TextView) root.findViewById(R.id.PerAge);
        TextView diagnostico = (TextView) root.findViewById(R.id.PerDiag);

        nombre.setText(DatosUsuario.getNombre() );
        apellidos.setText( DatosUsuario.getApellidos() );
        telefono.setText( DatosUsuario.getTelefono() );
        edad.setText( DatosUsuario.getEdad() );
        diagnostico.setText( "Diagnostico: \n" + DatosUsuario.getDiagnostico() );

        Button Editar = (Button) root.findViewById(R.id.Edit);
        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), EditPerfil.class);
                startActivityForResult(intent, 0);
            }
        });

        Button Guardar = (Button) root.findViewById(R.id.save);
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Principal.class);
                startActivityForResult(intent, 0);
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