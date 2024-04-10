package com.example.joaovitor_avaliacao.Model;

public class Jogo
{
    private String palavra;
    private int tentativas;

    public Jogo(String palavra)
    {
        this.palavra = palavra;
        this.tentativas = 0;
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

}
