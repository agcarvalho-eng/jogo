package com.example.jogo_palavras.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jogo_palavras.Model.Jogada;
import com.example.jogo_palavras.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter
{
    private LayoutInflater inflater;
    private ArrayList<Jogada> jogadas;

    public MyAdapter(LayoutInflater inflater, ArrayList<Jogada> jogadas) {
        this.inflater = inflater;
        this.jogadas = jogadas;
    }

    @Override
    public int getCount() {
        return jogadas.size();
    }
    @Override
    public Object getItem(int position) {
        return jogadas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Jogada jogada1 = jogadas.get(position);
        convertView = inflater.inflate(R.layout.pontuacao_layout, null);

        TextView ordem = convertView.findViewById(R.id.ordem);
        ordem.setText("Jogada N° "+jogada1.getOrdem());

        TextView pontuacao = convertView.findViewById(R.id.pontuacao);
        pontuacao.setText("Pontos: "+jogada1.getPontuacao());

        ImageView smiley = convertView.findViewById(R.id.smiley);

        if (jogada1.isGanhou()){
            smiley.setImageResource(R.drawable.smiley_happy);
        }else {
            smiley.setImageResource(R.drawable.smiley_sad);
        }
        return convertView;
    }
}
