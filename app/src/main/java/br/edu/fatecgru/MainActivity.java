package br.edu.fatecgru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    Button btnQuiz, btnSair;
    ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        edtNome = findViewById(R.id.edtNome);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnSair = findViewById(R.id.btnSair);
        imgAvatar = findViewById(R.id.imgAvatar);

        edtNome.addTextChangedListener(new TextWatcher() { //addTextChangedListener e TextWatcher -> vao ficar averiguando o que eu to digitando
            @Override
            public void afterTextChanged(Editable s) { //ignora
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { //ignora
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { //onTextChanged -> é chamado toda vez que há uma alteração no campo "Digite seu nome"
                //parâmetro s é o texto digitado no campo "Digite seu nome"
                String nome = s.toString().trim(); //trim() -> nao vai contar os espaços como resposta
                if (nome.isEmpty()) //podia ser -> btnQuiz.setEnable(!nome.isEmpty());
                    btnQuiz.setEnabled(false);
                else
                    btnQuiz.setEnabled(true);
            }
        });
    }
        public void sair(View v) {
        finish();
        }

        public void quiz(View v) {
        Intent it = new Intent(getApplicationContext(), QuizActivity.class);
        it.putExtra("nomeUsuario", edtNome.getText().toString());
        startActivity(it);
        }
}