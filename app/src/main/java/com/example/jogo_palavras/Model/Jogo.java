package com.example.jogo_palavras.Model;

import com.example.jogo_palavras.Singletons.DadosGlobais;

public class Jogo
{
    private String palavra;
    private int tentativas;
    private Jogada jogada;

    public Jogo(String palavra)
    {
        this.palavra = palavra;
        this.tentativas = 0;
        this.jogada = new Jogada(DadosGlobais.getInstance().getJogadas().size() + 1);
    }

    public boolean verificaDeducao(String deducao)
    {
        tentativas++;
        return palavra.equalsIgnoreCase(deducao);
    }

    public String getPalavra()
    {
        return palavra;
    }

    public int getTentativas()
    {
        return tentativas;
    }

    public Jogada getJogada() {
        return jogada;
    }


}
