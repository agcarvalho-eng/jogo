package com.example.jogo_palavras.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jogo_palavras.Adapters.MyAdapter;
import com.example.jogo_palavras.Model.Jogada;
import com.example.jogo_palavras.R;
import com.example.jogo_palavras.Singletons.DadosGlobais;

import java.util.ArrayList;
import java.util.Comparator;

public class PontuacoesActivity extends AppCompatActivity
{
    Button btnJogarNovamente;
    Button btnSairDoJogo;
    ListView listView;
    ArrayList<Jogada> jogadas;
    Boolean descrescente;
    MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pontuacoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        jogadas = DadosGlobais.getInstance().getJogadas();
        ordernarLista();
        configuraViews();

        btnJogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PontuacoesActivity.this, MainActivity.class));
            }
        });
        btnSairDoJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finishAffinity();
            }
        });
    }

    public void configuraViews()
    {
        btnJogarNovamente = findViewById(R.id.btnJogarNovamente);
        btnSairDoJogo = findViewById(R.id.btnSairDoJogo);
        listView = findViewById(R.id.listView);
        adapter = new MyAdapter(this.getLayoutInflater(), jogadas);
        listView.setAdapter(adapter);
    }

    private void ordernarLista()
    {
        Intent it = getIntent();
        descrescente = it.getBooleanExtra("descrescente", false);
        if(descrescente)
        {
            ordemDecrescentePorPontos();
        }else{
            ordemCrescente();
        }
    }

    public void ordemDecrescentePorPontos()
    {
        jogadas.sort(new Comparator<Jogada>() {
            @Override
            public int compare(Jogada o2, Jogada o1) {
                return Integer.compare(o2.getPontuacao(), o1.getPontuacao());
            }
        });
    }

    public void ordemCrescente()
    {
        jogadas.sort(new Comparator<Jogada>() {
            @Override
            public int compare(Jogada o2, Jogada o1) {
                return Integer.compare(o2.getOrdem(), o1.getOrdem());
            }
        });
    }
}