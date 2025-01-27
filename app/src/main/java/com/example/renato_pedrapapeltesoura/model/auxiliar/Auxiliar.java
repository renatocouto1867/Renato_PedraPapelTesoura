package com.example.renato_pedrapapeltesoura.model.auxiliar;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.renato_pedrapapeltesoura.model.Jogada;
import com.example.renato_pedrapapeltesoura.model.Jogo;
import com.google.gson.Gson;

import java.text.Normalizer;

public final class Auxiliar {
    private static final String JOGO_KEY = "jogo";
    private static final Gson gson = new Gson();
    private static Jogo jogo;

    static {
        jogo = new Jogo();
    }

    private Auxiliar() {
        throw new AssertionError("Classe utilitária não deve ser instanciada");
    }

    public static void zerarJogo(@NonNull Context context) {
        validateContext(context);
        jogo = new Jogo();
        salvarJogo(context);
    }

    public static Jogada novaJogada(int opcao) {
        return jogo.novaRodada(opcao);
    }

    public static void iniciarJogo(@NonNull Context context, @NonNull String nome) {
        validateContext(context);
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        jogo = new Jogo();

        jogo.setCodigoJogador(limpaString(nome));
        jogo.setNomeJogador(nome.trim());
        recuperarJogo(context);
    }

    public static void recuperarJogo(@NonNull Context context) {
        validateContext(context);

        if (jogo == null) {
            jogo = new Jogo();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(jogo.getNomeJogador(), 0);
        String jogoJson = sharedPreferences.getString(JOGO_KEY, "");

        if (!jogoJson.isEmpty()) {
            try {
                jogo = gson.fromJson(jogoJson, Jogo.class);
            } catch (Exception e) {
                jogo = new Jogo();
            }
        }
    }

    public static void salvarJogo(@NonNull Context context) {
        validateContext(context);

        try {
            String json = gson.toJson(jogo);

            SharedPreferences sharedPreferences = context.getSharedPreferences(jogo.getNomeJogador(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("jogo", json);
            editor.apply();

        } catch (Exception e) {
        }
    }

    public static String limpaString(@NonNull String str) {
        if (str == null) {
            throw new IllegalArgumentException("String de entrada não pode ser nula");
        }

        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll(" ", "")
                .toLowerCase();
    }

    private static void validateContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context não pode ser nulo");
        }
    }

    public static Jogo getJogo() {
        return jogo;
    }
}