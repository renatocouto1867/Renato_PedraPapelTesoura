package com.example.renato_pedrapapeltesoura.ui.placar;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.renato_pedrapapeltesoura.model.Jogo;
import com.example.renato_pedrapapeltesoura.model.auxiliar.Auxiliar;

public class PlacarViewModel extends ViewModel {
    private final MutableLiveData<Jogo> jogo;


    public PlacarViewModel() {
        jogo = new MutableLiveData<>();
    }

    public void getJogo() {
        jogo.postValue(Auxiliar.jogo);
    }

    public LiveData<Jogo> retornaJogo() {
        return jogo;
    }
    public void zerar(Context context){
        Auxiliar.zerarJogo(context);
    }

}