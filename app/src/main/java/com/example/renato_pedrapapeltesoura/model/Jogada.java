package com.example.renato_pedrapapeltesoura.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Classe que representa uma jogada no jogo
 * */
public class Jogada implements Serializable {
    private final int opcaoJogador;  // Escolha do jogador (0: Pedra, 1: Papel, 2: Tesoura)
    private final int opcaoAndroid; // Escolha do Android (gerada aleatoriamente)
    private final boolean empate;   // Indica se houve empate na jogada
    private final Integer vencedor; // 0: Empate, 1: Jogador venceu, 2: Android venceu

    public Jogada(int opcaoSelecionada) {
        this.opcaoJogador = opcaoSelecionada;
        this.opcaoAndroid = geraNumeroAleatorio();
        this.empate = opcaoJogador == opcaoAndroid;
        this.vencedor = determinarVencedor();
    }

    public int getVencedor() {
        return vencedor;
    }

    private int determinarVencedor() {
        if (empate) {
            return 0; // Empate
        }
        return verificaVitoria(opcaoJogador) ? 1 : 2;
    }

    private boolean verificaVitoria(int escolhaJogador) {
        final int PEDRA = 0;
        final int PAPEL = 1;
        final int TESOURA = 2;

        switch (escolhaJogador) {
            case PEDRA:
                return opcaoAndroid == TESOURA;
            case PAPEL:
                return opcaoAndroid == PEDRA;
            case TESOURA:
                return opcaoAndroid == PAPEL;
            default:
                return false;
        }
    }

    private int geraNumeroAleatorio() {
        return new Random().nextInt(3);
    }

    public int getOpcaoAndroid() {
        return opcaoAndroid;
    }

    public int getOpcaoJogador() {
        return opcaoJogador;
    }
}
