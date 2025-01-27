package com.example.renato_pedrapapeltesoura.ui.resultado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.databinding.FragmentResultadoBinding;
import com.example.renato_pedrapapeltesoura.ui.jogada.JogadaFragment;
import com.example.renato_pedrapapeltesoura.ui.placar.PlacarFragment;

public class ResultadoFragment extends Fragment {
    private static final String ARG_VENCEDOR = "vencedor";
    private static final String ARG_ESCOLHA_ANDROID = "escolhaAndroid";
    private FragmentResultadoBinding binding;
    private int vencedor;
    private int escolhaAndroid;

    public ResultadoFragment() {

    }

    public static ResultadoFragment newInstance(int vencedor, int escolhaAndroid) {
        ResultadoFragment fragment = new ResultadoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_VENCEDOR, vencedor);
        args.putInt(ARG_ESCOLHA_ANDROID, escolhaAndroid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            vencedor = getArguments().getInt(ARG_VENCEDOR);
            escolhaAndroid = getArguments().getInt(ARG_ESCOLHA_ANDROID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentResultadoBinding.inflate(inflater, container, false);

        exibeResultado();
        configuraClick();

        return binding.getRoot();
    }

    private void configuraClick() {
        binding.buttonNovoJogo.setOnClickListener(view -> {
            iniciarFragment(JogadaFragment.newInstance());

        });

        binding.buttonPlacar.setOnClickListener(view -> {
            iniciarFragment(PlacarFragment.newInstance());
        });
        binding.buttonSair.setOnClickListener(view -> {
            sair();
        });
    }


    private void exibeResultado() {
        if (vencedor == 0) {
            binding.textViewResultado.setText(R.string.voce_empatou);
            defineImagem(escolhaAndroid);
        } else if (vencedor == 1) {
            binding.textViewResultado.setText(R.string.voce_ganhou);
        } else if (vencedor == 2) {
            binding.textViewResultado.setText(R.string.voce_perdeu);
        } else if (vencedor == 3) {
            binding.textViewResultado.setText(R.string.ninguem_ganhou);
        } else {
            binding.textViewResultado.setText(R.string.erro_no_sistema);
        }
        defineImagem(escolhaAndroid);
    }

    private void defineImagem(int sorteio) {
        switch (sorteio) {
            case 0:
                binding.imageViewResultado.setImageResource(R.drawable.pedra);
                break;
            case 1:
                binding.imageViewResultado.setImageResource(R.drawable.papel);
                break;
            case 2:
                binding.imageViewResultado.setImageResource(R.drawable.tesoura);
                break;
            default:
                binding.imageViewResultado.setImageResource(R.drawable.pedra_papel_tesoura);
        }
    }

    private void iniciarFragment(Fragment fragment) {

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void sair() {
        requireActivity().finish();
    }
}
