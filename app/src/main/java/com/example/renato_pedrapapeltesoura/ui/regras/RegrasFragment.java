package com.example.renato_pedrapapeltesoura.ui.regras;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.databinding.FragmentRegrasBinding;
import com.example.renato_pedrapapeltesoura.ui.inicial.InicialFragment;

public class RegrasFragment extends Fragment {
    private FragmentRegrasBinding binding;
    private Uri uri;

    public RegrasFragment() {

    }

    public static RegrasFragment newInstance() {
        RegrasFragment fragment = new RegrasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegrasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video);
        binding.videoRegras.setVideoURI(uri);

        configuraClick();

        return root;
    }

    private void configuraClick() {

        binding.buttonPlay.setOnClickListener(view -> {
            binding.videoRegras.start();
            int posicao = binding.videoRegras.getDuration();//obter o total
        });

        binding.buttonPause.setOnClickListener(view -> {
            binding.videoRegras.pause();
            int posicao = binding.videoRegras.getCurrentPosition();
        });


        binding.buttonStop.setOnClickListener(view -> {
            binding.videoRegras.stopPlayback();
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            iniciarFragment(InicialFragment.newInstance());
        });
    }

    private void iniciarFragment(Fragment fragment) {

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


}