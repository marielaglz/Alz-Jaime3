package com.example.alz_jaime.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
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

import com.example.alz_jaime.DatosAlarmas;
import com.example.alz_jaime.EditAlarma;
import com.example.alz_jaime.EditPersonaAcargo;
import com.example.alz_jaime.Principal;
import com.example.alz_jaime.R;
import com.example.alz_jaime.TipoMedicamentos;
import com.example.alz_jaime.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView DA = (TextView) root.findViewById(R.id.DiaAlarmas);
        DA.setText( DatosAlarmas.getFullRegistro() );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}