package com.example.joaovitor_avaliacao.View;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.joaovitor_avaliacao.R;
import com.example.joaovitor_avaliacao.Utils.Auxiliar_Sorteio;

public class MainActivity extends AppCompatActivity {
    private Button btnJogar;
    private EditText edtTextNome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnJogar = findViewById(R.id.btnJogar);
        edtTextNome = findViewById(R.id.edtNome);

        btnJogar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String jogador = edtTextNome.getText().toString();
                if (!jogador.equals("")){
                    int numero_aleatorio = Auxiliar_Sorteio.geraNumeroAleatorio();
                    String palavra_sorteada = Auxiliar_Sorteio.sorteiaPalavra(MainActivity.this, numero_aleatorio);

                    Intent it = new Intent(MainActivity.this, GameActivity.class);
                    it.putExtra("jogador", jogador);
                    it.putExtra("numero", numero_aleatorio);
                    it.putExtra("palavra", palavra_sorteada);

                    Toast.makeText(MainActivity.this, "Posição:"+numero_aleatorio, Toast.LENGTH_SHORT).show();

                    startActivity(it);
                }else {
                    Toast.makeText(MainActivity.this, "Por favor informe seu nome!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}