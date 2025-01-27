package com.example.renato_pedrapapeltesoura.ui.placar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.databinding.FragmentPlacarBinding;
import com.example.renato_pedrapapeltesoura.model.Jogo;
import com.example.renato_pedrapapeltesoura.ui.jogada.JogadaFragment;

public class PlacarFragment extends Fragment {

    private PlacarViewModel viewModel;
    private FragmentPlacarBinding binding;

    public static PlacarFragment newInstance() {
        return new PlacarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPlacarBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(PlacarViewModel.class);
        viewModel.getJogo();

        viewModel.retornaJogo().observe(getViewLifecycleOwner(), jogo -> {
            exibePlacar(jogo);
        });

        configuraClick();

        return root;
    }

    private void configuraClick() {
        binding.buttonNovo.setOnClickListener(view -> {
            iniciarFragment(JogadaFragment.newInstance());
        });

        binding.buttonSair.setOnClickListener(view -> {
            requireActivity().finish();
        });

        binding.buttonZera.setOnClickListener(view -> {
            zeraJogo();
        });
    }

    private void zeraJogo() {
        viewModel.zerar(requireActivity());
        viewModel.getJogo();
    }

    private void exibePlacar(Jogo jogo) {
        binding.textViewPrimeiro.setText(jogo.getPrimeiroColocado(requireContext()));
        binding.textViewSegundo.setText(jogo.getSegundoColocado(requireContext()));
    }

    private void iniciarFragment(Fragment fragment) {

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}