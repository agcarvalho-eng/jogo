package com.example.jogo_palavras.Utils;

import android.content.Context;
import com.example.jogo_palavras.R;

import java.util.Random;

public class Auxiliar_Sorteio {

    public static int geraNumeroAleatorio()
    {
        return new Random().nextInt(10);
    }

    public static String sorteiaPalavra(Context context, int indice)
    {
        String[] palavras = context.getResources().getStringArray(R.array.palavras);

        //indice = indice - 1; //devido a lista no arquivo começar no indice 0

        return palavras[indice];
    }
}
