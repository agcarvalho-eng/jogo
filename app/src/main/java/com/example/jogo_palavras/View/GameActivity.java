package com.example.jogo_palavras.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jogo_palavras.Model.Jogo;
import com.example.jogo_palavras.R;
import com.example.jogo_palavras.Singletons.DadosGlobais;

public class GameActivity extends AppCompatActivity
{
    private Jogo jogo;
    private TextView textViewDica1;
    private TextView textViewDica2;
    private TextView textViewDica3;
    private Button btnJogar;
    private EditText edtNome;
    private ImageView vida1;
    private ImageView vida2;


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
        configuraViews();
        iniciarJogo();
        btnJogar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String textInformado = edtNome.getText().toString().trim().toUpperCase();

                if (!textInformado.equals("")){
                    if(!jogo.verificaDeducao(textInformado)){
                        deducaoIncorreta();
                    }else{
                        deducaoCorreta();
                    }
                }else {
                    Toast.makeText(GameActivity.this, "Por favor, informe uma palavra!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void configuraViews()
    {
        textViewDica1 = findViewById(R.id.textViewDica1);
        textViewDica2 = findViewById(R.id.textViewDica2);
        textViewDica3 = findViewById(R.id.textViewDica3);
        edtNome = findViewById(R.id.edtNome);
        btnJogar = findViewById(R.id.btnJogar);
        vida1 = findViewById(R.id.vida1);
        vida2 = findViewById(R.id.vida2);
    }

    private void iniciarJogo()
    {
        Intent it = getIntent();

        String palavra = it.getStringExtra("palavra");
        jogo = new Jogo(palavra);

        montaToolBar(it.getStringExtra("jogador"));

        String[] dicas = getDicas(this, it.getIntExtra("numero", 0));
        textViewDica1.setText("Dica 1: "+dicas[0]);
        textViewDica2.setText("Dica 2: "+dicas[1]);
        textViewDica3.setText("Dica 3: "+dicas[2]);
    }

    private void deducaoIncorreta()
    {
        switch (jogo.getTentativas())
        {
            case 1:
                jogo.getJogada().errou();
                textViewDica2.setVisibility(View.VISIBLE);
                vida1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                jogo.getJogada().errou();
                textViewDica3.setVisibility(View.VISIBLE);
                vida2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                jogo.getJogada().errou();
                DadosGlobais.getInstance().getJogadas().add(jogo.getJogada());
                MyDialog dialog = new MyDialog("Game Over! Deseja jogar novamente?");
                dialog.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void deducaoCorreta()
    {
        DadosGlobais.getInstance().getJogadas().add(jogo.getJogada());
        MyDialog dialog = new MyDialog("Você acertou! Deseja jogar novamente?");
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    private String[] getDicas(Context context, int palavraPosicao)
    {
        String[] dicas_xml = context.getResources().getStringArray(R.array.dicas);

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
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    public void montaToolBar(String nome_jogador)
    {
        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Jogador: "+nome_jogador);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
       if (item.getTitle().equals("Jogar novamente"))
       {
           jogarNovamente();
       }else if(item.getTitle().equals("Pontuação"))
       {
           verPontuacoes();
       } else if (item.getTitle().equals("Sair"))
       {
           finishAffinity();
       }
        return true;
    }

    public void jogarNovamente()
    {
        MyDialog dialog = new MyDialog("Deseja jogar novamente?");
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void verPontuacoes()
    {
        Intent it = new Intent(GameActivity.this, PontuacoesActivity.class);
        it.putExtra("descrescente", true);
        startActivity(it);
    }

}