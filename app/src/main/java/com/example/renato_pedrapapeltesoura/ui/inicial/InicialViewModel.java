package com.example.renato_pedrapapeltesoura.ui.inicial;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.renato_pedrapapeltesoura.model.Jogo;
import com.example.renato_pedrapapeltesoura.model.auxiliar.Auxiliar;

public class InicialViewModel extends ViewModel {
    private final MutableLiveData<Jogo> jogo;

    public void setJogador(String nome){
        Auxiliar.jogo.setNomeJogador(nome);
    }

    public InicialViewModel(){
        jogo = new MutableLiveData<>();
    }

    private void carregaJogo() {
        jogo.postValue(Auxiliar.jogo);
    }

    public MutableLiveData<Jogo> getJogo(){
        return jogo;
    }
}
