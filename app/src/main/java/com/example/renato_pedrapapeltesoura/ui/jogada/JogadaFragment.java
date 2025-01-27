package com.example.renato_pedrapapeltesoura.ui.jogada;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.databinding.FragmentJogadaBinding;
import com.example.renato_pedrapapeltesoura.ui.resultado.ResultadoFragment;

import java.util.Locale;

public class JogadaFragment extends Fragment implements TextToSpeech.OnInitListener {

    private FragmentJogadaBinding binding;
    private TextToSpeech textToSpeech;
    private String msg;
    private JogadaViewModel viewModel;

    public JogadaFragment() {

    }

    public static JogadaFragment newInstance() {
        JogadaFragment fragment = new JogadaFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textToSpeech = new TextToSpeech(requireContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentJogadaBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(JogadaViewModel.class);

        viewModel.getRetornoJoga().observe(getViewLifecycleOwner(), jogada -> {
            iniciarFragment(ResultadoFragment.newInstance(jogada.getVencedor(), jogada.getOpcaoAndroid()));
        });

        msg = getString(R.string.selecione_uma_opcao);
        configuraClick();

        return binding.getRoot();
    }

    private void configuraClick() {
        binding.buttonPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.criaJogada(0, requireActivity());
            }
        });

        binding.buttonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.criaJogada(1, requireActivity());
            }
        });

        binding.buttonTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.criaJogada(2, requireActivity());
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("pt", "br");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(1.2f);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("problma", "idioma não suportado");
            } else {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);

                } else {
                    textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        }
    }

    private void iniciarFragment(Fragment fragment) {

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
