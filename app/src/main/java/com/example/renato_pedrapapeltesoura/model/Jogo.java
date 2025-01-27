package com.example.renato_pedrapapeltesoura.model;

import android.content.Context;

import com.example.renato_pedrapapeltesoura.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogo implements Serializable {
    private String nomeJogador;
    private String codigoJogador;


    private List<Jogada> jogadas;


    public Jogo(String nomeJogador, String codigoJogador) {
        this.nomeJogador = nomeJogador;
        this.codigoJogador = codigoJogador ;
        this.jogadas = new ArrayList<>();
    }

    public String getCodigoJogador() {
        return codigoJogador;
    }

    public void setCodigoJogador(String codigoJogador) {
        this.codigoJogador = codigoJogador;
    }

    public Jogo() {
        this.nomeJogador = "";
        this.jogadas = new ArrayList<>();
    }

    public ArrayList<Jogada> getJogadas() {
        return (ArrayList<Jogada>) jogadas;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getPontosUsuario() {
        int pontos = 0;
        for (Jogada jogada : jogadas) {
            if ((jogada.getVencedor() == 1) || (jogada.getVencedor() == 0)) {
                pontos++;
            }
        }
        return pontos;
    }

    public int getPontosAndroid() {
        int pontos = 0;
        for (Jogada jogada : jogadas) {
            if (jogada.getVencedor() == 2 || jogada.getVencedor() == 0) {
                pontos++;
            }
        }
        return pontos;
    }

    public Jogada novaRodada(int opcao) {
        Jogada jogada = new Jogada(opcao);
        jogadas.add(jogada);
        return jogada;
    }

    public void zerarJogo() {
        jogadas = null;
        jogadas = new ArrayList<>();
    }

    public String getPrimeiroColocado(Context context) {
        int pontosUsuario = getPontosUsuario();
        int pontosAndroid = getPontosAndroid();
        if (pontosUsuario <= pontosAndroid)
            return context.getString(R.string.andoid) + pontosAndroid;
        else return context.getString(R.string.usuario) + pontosUsuario;
    }

    public String getSegundoColocado(Context context) {
        int pontosUsuario = getPontosUsuario();
        int pontosAndroid = getPontosAndroid();
        if (pontosUsuario > pontosAndroid)
            return context.getString(R.string.andoid) + pontosAndroid;
        else return context.getString(R.string.usuario) + pontosUsuario;
    }

}