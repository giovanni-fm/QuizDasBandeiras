package br.edu.fatecgru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoActivity extends AppCompatActivity {

    TextView tvNomeRank, tvPontosFinais;
    Button btnNovamente, btnTelaPrincipal;

    int acertos;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvNomeRank = findViewById(R.id.tvNomeRank);
        tvPontosFinais = findViewById(R.id.tvPontosFinais);
        btnNovamente = findViewById(R.id.btnNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);

        acertos = getIntent().getIntExtra("pontos", 0); //tem que estar aqui dentro
        nome = getIntent().getStringExtra("nomeUsuario");

        tvPontosFinais.setText(String.valueOf(acertos));
        tvNomeRank.setText(nome);
    }

    public void reiniciar(View v) {
        Intent it = new Intent(this, QuizActivity.class);
        it.putExtra("nomeUsuario", nome);
        startActivity(it);
        finish();
    }

    public void telaPrincipal(View v) {
        finish();
    }
}