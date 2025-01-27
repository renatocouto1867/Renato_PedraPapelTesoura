package com.example.renato_pedrapapeltesoura.ui.principal;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.example.renato_pedrapapeltesoura.model.auxiliar.Auxiliar;

public class MainActivityViewModel extends ViewModel {


    public void salvaHistorico(Activity context) {
        Auxiliar.salvarJogo(context);
    }


}
