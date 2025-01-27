package com.example.renato_pedrapapeltesoura.ui.jogada;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.renato_pedrapapeltesoura.model.Jogada;
import com.example.renato_pedrapapeltesoura.model.auxiliar.Auxiliar;

public class JogadaViewModel extends ViewModel {

    private final MutableLiveData<String> msResult;
    private final MutableLiveData<Jogada> retornaJogada;


    public JogadaViewModel() {
        msResult = new MutableLiveData<>();
        retornaJogada = new MutableLiveData<Jogada>();
    }

    public void criaJogada(int opcao, Context context) {

        Jogada jogada = Auxiliar.jogo.novaRodada(opcao);
        Auxiliar.salvarJogo(context);
        retornaJogada.postValue(jogada);
    }

    public LiveData<String> getMsResult() {

        return msResult;
    }

    public LiveData<Jogada> getRetornoJoga() {
        return retornaJogada;
    }

}
