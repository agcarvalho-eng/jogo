package com.example.joaovitor_avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class Terceira_activity extends AppCompatActivity {

    TextView textViewPalavra;
    TextView textViewDica1;
    TextView textViewDica2;
    TextView textViewDica3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terceira);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewPalavra = findViewById(R.id.textViewPalavra);
        textViewDica1 = findViewById(R.id.textViewDica1);
        textViewDica2 = findViewById(R.id.textViewDica2);
        textViewDica3 = findViewById(R.id.textViewDica3);

        Intent it = getIntent();
        String nome_jogador = it.getStringExtra("jogador");

        textViewPalavra.setText(it.getStringExtra("palavra"));

        Toolbar mytoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(nome_jogador);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    private ArrayList<String> getDicas()
    {
        String[] dicas = getResources().getStringArray(R.array.dicas);

        ArrayList<String> lisdicas = new ArrayList<>();

        for (int i = 0; i < dicas.length; i++)
        {
            String dica = dicas[i];
            int posicao = i * 3;
            String[] itemsDicas = new String[]{
                    dicas[posicao],
                    dicas[posicao + 1],
                    dicas[posicao + 2],
            };
            lisdicas.add(Arrays.toString(itemsDicas));

            //não deu tempo continuar a implementação
        }

        return lisdicas;
    }
}