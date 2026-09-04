package br.edu.fatecgru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    RadioGroup rdgOpcoes;
    RadioButton rdbOpcao1, rdbOpcao2, rdbOpcao3, rdbOpcao4;
    ImageView imgBandeira;
    Button btnConfirma;

    int indiceAtual = 0;
    int pontos = 0;
    List<Pergunta> listaDePerguntas = new ArrayList<>(); //lista onde contem TODAS as perguntas e respostas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rdbOpcao1 = findViewById(R.id.rdbOpcao1);
        rdbOpcao2 = findViewById(R.id.rdbOpcao2);
        rdbOpcao3 = findViewById(R.id.rdbOpcao3);
        rdbOpcao4 = findViewById(R.id.rdbOpcao4);
        rdgOpcoes = findViewById(R.id.rdgOpcoes);
        btnConfirma = findViewById(R.id.btnConfirma);
        imgBandeira = findViewById(R.id.imgBandeira);

        listaDePerguntas.add(new Pergunta(R.drawable.um, "Estados Unidos da América", new String[]{"Estados Unidos da América", "Argentina", "Chile", "México"})); //pergunta 1 (..) pergunta 10, esse eh o modelo pra criar uma lista contendo: imagem, resposta certa e alternativas (em Array)
        listaDePerguntas.add(new Pergunta(R.drawable.dois, "Alemanha", new String[]{"Bélgica", "Peru", "Alemanha", "Puerto Rico"}));
        listaDePerguntas.add(new Pergunta(R.drawable.tres, "Itália", new String[]{"Nigéria", "França", "Brasil", "Itália"}));
        listaDePerguntas.add(new Pergunta(R.drawable.quatro, "Bósnia e Herzegovina", new String[]{"Bósnia e Herzegovina", "Bulgária", "Sérvia", "Bielorrússia"}));
        listaDePerguntas.add(new Pergunta(R.drawable.cinco, "Brasil", new String[]{"Brasil", "Noruega", "Croácia", "Alemanha"}));
        listaDePerguntas.add(new Pergunta(R.drawable.seis, "Espanha", new String[]{"Portugal", "Espanha", "Andorra", "Quênia"}));
        listaDePerguntas.add(new Pergunta(R.drawable.sete, "Escócia", new String[]{"Tonga", "Irlanda", "País de Gales", "Escócia"}));
        listaDePerguntas.add(new Pergunta(R.drawable.oito, "Taiwan", new String[]{"China", "Taiwan", "Tailândia", "Vietnã"}));
        listaDePerguntas.add(new Pergunta(R.drawable.nove, "Uruguai", new String[]{"Uruguai", "Argentina", "Cuba", "Paraguai"}));
        listaDePerguntas.add(new Pergunta(R.drawable.dez, "Austrália", new String[]{"Austrália", "Nova Zelândia", "Inglaterra", "Ilhas Virgens Britânicas"}));
        exibirPergunta();
    }

    public class Pergunta { //classe pergunta
        private int imgBandeira; //ID da bandeira
        private String correto; //se a resposta estiver certa
        private String[] opcoes; //Array onde vai conter as opções nos botões

        public Pergunta(int imgBandeira, String correto, String[] opcoes) {
            this.imgBandeira = imgBandeira;
            this.correto = correto;
            this.opcoes = opcoes;
        }

        public int getImgBandeira() { return imgBandeira; }
        public String getCorreto() { return correto; }
        public String[] getOpcoes() { return opcoes; }
    }

    private void exibirPergunta() {
        Pergunta p = listaDePerguntas.get(indiceAtual);

        imgBandeira.setImageResource(p.getImgBandeira()); //adiciona a imagem na lista
        rdbOpcao1.setText(p.getOpcoes()[0]); //muda a opção de acordo com o que ta na lista
        rdbOpcao2.setText(p.getOpcoes()[1]);
        rdbOpcao3.setText(p.getOpcoes()[2]);
        rdbOpcao4.setText(p.getOpcoes()[3]);

        rdgOpcoes.clearCheck(); //limpa os botões
    }

    public void confimar(View v) {
        int selecionado = rdgOpcoes.getCheckedRadioButtonId(); //verifica se tem algo marcado

        if (selecionado == -1) { //-1 -> nao tem nada selecionado || 0 -> tem algo selecionado
            Toast.makeText(this, "Selecione uma resposta", Toast.LENGTH_SHORT).show(); //manda uma mensagem rapida dizendo pra selecionar algo
            return;
        }

        RadioButton rdbSelecionado = findViewById(selecionado); //variavel temporaria, so vai marcar qual opção foi selecionada
        String respUsuario = rdbSelecionado.getText().toString(); //vai converter em String a resposta

        Pergunta perguntaAtual = listaDePerguntas.get(indiceAtual); //vai pegar de acordo com indice (pergunta) está
        if (respUsuario.equals(perguntaAtual.getCorreto())) //se a resposta do usuario estiver de acordo com a lista = ponto
            pontos++;

        //continua as perguntas até acabar
        indiceAtual++; //avança

        if(indiceAtual < listaDePerguntas.size()){ //se o indice atual for menor que o numero de perguntas = avança
            exibirPergunta();
        } else { //caso for maior = vai pra tela de RESULTADOS
            Intent it = new Intent(QuizActivity.this, ResultadoActivity.class);
            it.putExtra("pontos", pontos); //vai passar os pontos pra proxima tela
            String usuario = getIntent().getStringExtra("nomeUsuario"); //vai pegar o nome do usuario e converte pra String
            it.putExtra("nomeUsuario", usuario); //passa pra tela de Resultados
            startActivity(it);
            finish(); //finaliza SOMENTE a tela do Quiz, indo pra tela de RESULTADOS
        }
    }
}