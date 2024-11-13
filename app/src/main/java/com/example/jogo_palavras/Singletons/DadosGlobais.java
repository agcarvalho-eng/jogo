package com.example.jogo_palavras.Singletons;

import com.example.jogo_palavras.Model.Jogada;

import java.util.ArrayList;

public class DadosGlobais
{
    private static DadosGlobais instance;
    private ArrayList<Jogada> jogadas;
    public DadosGlobais()
    {
        this.jogadas = new ArrayList<>();
    }
    public static synchronized DadosGlobais getInstance()
    {
        if (instance == null) {
            instance = new DadosGlobais();
        }
        return instance;
    }

    public ArrayList<Jogada> getJogadas()
    {
        return jogadas;
    }
}
