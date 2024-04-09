package com.example.joaovitor_avaliacao;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity
{
    TextView textViewPalavra;
    TextView textViewDica1;
    TextView textViewDica2;
    TextView textViewDica3;
    Button btnJogar;
    EditText edtNome;
    ImageView vida1;
    ImageView vida2;
    ImageView vida3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewPalavra = findViewById(R.id.textViewPalavra);
        textViewDica1 = findViewById(R.id.textViewDica1);
        textViewDica2 = findViewById(R.id.textViewDica2);
        textViewDica3 = findViewById(R.id.textViewDica3);
        edtNome = findViewById(R.id.edtNome);
        btnJogar = findViewById(R.id.btnJogar);
        vida1 = findViewById(R.id.vida1);
        vida2 = findViewById(R.id.vida2);
        vida3 = findViewById(R.id.vida3);

        Intent it = getIntent();
        String palavra = it.getStringExtra("palavra");
        String jogador = it.getStringExtra("jogador");

        montaToolBar(jogador);

        int posicao = it.getIntExtra("numero", 0);
        String[] dicas = getDicas(this, posicao);

        textViewDica1.setText("Dica 1: "+dicas[0]);
        textViewDica2.setText("Dica 2: "+dicas[1]);
        textViewDica3.setText("Dica 3: "+dicas[2]);

        btnJogar.setOnClickListener(new View.OnClickListener()
        {
            int tentativas = 0;
            @Override
            public void onClick(View v)
            {
                String textInformado = edtNome.getText().toString().trim().toUpperCase();
                if(!palavra.equals(textInformado)){
                    tentativas++;
                    switch (tentativas) {
                        case 1:
                            textViewDica2.setVisibility(View.VISIBLE);
                            vida1.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            textViewDica3.setVisibility(View.VISIBLE);
                            vida2.setVisibility(View.INVISIBLE);
                            break;
                    }
                }else{
                    Toast.makeText(GameActivity.this, "Acertou!!", Toast.LENGTH_SHORT).show();
                    textViewPalavra.setText("Parabéns! A palavra enigmática é  "+palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase());
                    textViewPalavra.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private String[] getDicas(Context context, int palavraPosicao)
    {
        Resources res = context.getResources();

        String[] dicas_xml = res.getStringArray(R.array.dicas);

        int posicaoInicial = palavraPosicao * 3;

        String[] dicas = new String[]{
                dicas_xml[posicaoInicial],
                dicas_xml[posicaoInicial + 1],
                dicas_xml[posicaoInicial + 2]
        };
        return dicas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void montaToolBar(String nome_jogador)
    {
        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(nome_jogador);
    }

}