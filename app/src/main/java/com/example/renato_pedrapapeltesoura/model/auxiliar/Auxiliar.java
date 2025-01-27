package com.example.renato_pedrapapeltesoura.model.auxiliar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.renato_pedrapapeltesoura.model.Jogo;
import com.google.gson.Gson;

import java.text.Normalizer;


public class Auxiliar {
    public static Jogo jogo;

    static {
        jogo = new Jogo();
    }


    public static void zerarJogo(Context context) {
        jogo.zerarJogo();
        salvarJogo(context);
    }


    public static void recuparaJogo(Context context) {
        if (jogo == null) {
            jogo = new Jogo(); // Inicializa a variável jogo se estiver null
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(jogo.getNomeJogador(), 0);
        String jogoJson = sharedPreferences.getString("jogo", "");
        if (!jogoJson.equals("")) {
            Gson gson = new Gson();
            Auxiliar.jogo = gson.fromJson(jogoJson, Jogo.class);
        }
    }


    public static String limpaString(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "")
                .toLowerCase();
    }
    public static void salvarJogo(Context context) {
        Gson gson = new Gson();
        String json = gson.toJson(Auxiliar.jogo);
        SharedPreferences sharedPreferences = context.getSharedPreferences(jogo.getNomeJogador(), 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jogo", json);
        editor.apply();
    }
}
