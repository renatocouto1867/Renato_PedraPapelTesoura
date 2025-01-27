package com.example.renato_pedrapapeltesoura.ui.inicial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.databinding.FragmentIncialBinding;
import com.example.renato_pedrapapeltesoura.ui.jogada.JogadaFragment;
import com.example.renato_pedrapapeltesoura.ui.regras.RegrasFragment;

public class InicialFragment extends Fragment {
    private FragmentIncialBinding binding;
    private InicialViewModel viewModel;

    public InicialFragment() {

    }

    public static InicialFragment newInstance() {
        InicialFragment fragment = new InicialFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIncialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(InicialViewModel.class);

        configuraClick();

        return root;
    }

    private void configuraClick() {
        binding.buttonNovoJogar.setOnClickListener(view -> {
            novoJogo();
        });

        binding.buttonRegras.setOnClickListener(view -> {
            iniciarFragment(RegrasFragment.newInstance());
        });
    }

    private void iniciarFragment(Fragment fragment) {

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void novoJogo() {
        if (validaCampos()) {
            viewModel.iniciaJogo(requireActivity(), binding.editNomeJogador.getText().toString());
            iniciarFragment(JogadaFragment.newInstance());

        } else {
            mensagemErroCampoJogador();
        }
    }

    private boolean validaCampos() {

        return !(binding.editNomeJogador.getText().toString().isEmpty() || binding.editNomeJogador.getText().toString().isBlank());
    }

    private void mensagemErroCampoJogador() {
        binding.editNomeJogador.requestFocus();
        binding.editNomeJogador.setError(getString(R.string.diginte_seu_nome));
    }
}