package com.example.renato_pedrapapeltesoura.ui.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.databinding.FragmentEnviaEmailBinding;
import com.example.renato_pedrapapeltesoura.ui.jogada.JogadaFragment;

public class EnviaEmailFragment extends Fragment {
    private static final String ARG_RESULTADO = "mensagem";
    String mensagemEnviar;
    String titulo = "Resultado da rodada";
    private FragmentEnviaEmailBinding binding;

    public static EnviaEmailFragment newInstance(String mensagem) {
        EnviaEmailFragment fragment = new EnviaEmailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RESULTADO, mensagem);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mensagemEnviar = getArguments().getString(ARG_RESULTADO);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEnviaEmailBinding.inflate(inflater, container, false);

        configuraClick();
        binding.editEmail.requestFocus();

        return binding.getRoot();
    }

    private void configuraClick() {
        binding.buttonEnviar.setOnClickListener(view -> {
            enviaEmail();
        });

        binding.buttonCancelar.setOnClickListener(view -> {
            cancelar();
        });
    }

    private void cancelar() {
        iniciarFragment(JogadaFragment.newInstance());
    }

    private void iniciarFragment(Fragment fragment) {

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    //   envia email.
    public void enviaEmail() {

        try {
            Intent intent = null;

            String email = binding.editEmail.getText().toString().trim();

            String text = "My Text";
            intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, titulo);
            intent.putExtra(Intent.EXTRA_TEXT, mensagemEnviar);
            intent.setType("message/rfc822");
            intent.setPackage("com.google.android.gm");
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(requireContext(),
                    "Erro ao enviar email, verifique suas configurações! codigo erro: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        binding.buttonEnviar.setOnClickListener(view -> {
        });

        binding.buttonCancelar.setOnClickListener(view -> {
        });
    }
}