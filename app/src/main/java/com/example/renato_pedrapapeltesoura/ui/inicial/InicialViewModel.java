package com.example.renato_pedrapapeltesoura.ui.inicial;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.example.renato_pedrapapeltesoura.model.auxiliar.Auxiliar;

public class InicialViewModel extends ViewModel {


    public void iniciaJogo(Activity context, String nome) {

        Auxiliar.iniciarJogo(context, nome);
    }


}
