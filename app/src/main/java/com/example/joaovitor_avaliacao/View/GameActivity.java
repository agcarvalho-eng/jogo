package com.example.joaovitor_avaliacao.View;

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
import com.example.joaovitor_avaliacao.R;

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
                        case 3:
                            startActivity(new Intent(GameActivity.this, GameOverActivity.class));
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
        getSupportActionBar().setTitle(nome_jogador);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item)
//    {
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.item1:
//                startActivity(new Intent(GameActivity.this, PontuacoesActivity.class));
//                return true;
//            case R.id.item2:
//                startActivity(new Intent(GameActivity.this, PontuacoesActivity.class));
//                return true;
//            case R.id.item3:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}