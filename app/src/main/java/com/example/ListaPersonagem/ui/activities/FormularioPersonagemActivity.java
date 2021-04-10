package com.example.ListaPersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ListaPersonagem.dao.PersonagenDao;
import com.example.ListaPersonagem.model.Personagen;
import com.example.auladia11.R;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private   EditText campoNome;
    private   EditText campoNacimento;
    private   EditText campoAltura;

   private String nome;
   private String nascimento;
   private String altura;

    private  final  PersonagenDao dao = new PersonagenDao();
     private Personagen Personagen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulario Personagens");

        inicializacaoCampos();
        configuraBotao();

        Intent dados = getIntent();
        if (dados.hasExtra( "personagen"))
        {
            Personagen personagen = (Personagen) dados.getSerializableExtra("personagen");
            campoNome.setText(personagen.getNome());
            campoNacimento.setText(personagen.getNacimento());
            campoAltura.setText(personagen.getAltura());
        }
        else
        {
            Personagen  = new Personagen();
        }
    }

    private void configuraBotao() {
        // Pegando  o botao de salvar, para por um listener de ações nele, no caso, salvar  informaçõa do personagem.
        Button btSalvar =  findViewById(R.id.bt_salvar);
        btSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nome = campoNome.getText().toString();
               nascimento = campoNacimento.getText().toString();
               altura = campoAltura.getText().toString();

                Personagen personagemSalvo = new Personagen(nome,nascimento,altura);
                dao.salva(personagemSalvo);
                finish();// finalizando a Activity do formulario sem sobre escrever a lista


                // aqui set(salva) os dados do personagem para que se edite depois
                personagemSalvo.setNome(nome);
                personagemSalvo.setNacimento(nascimento);
                personagemSalvo.setAltura(altura);
                dao.editar(personagemSalvo);

            }
        }
        );
    }

    private void inicializacaoCampos()
    {//Pegando os  ids referentes aos dados do personagem
        campoNome = findViewById(R.id.editTextText_Name);
        campoNacimento = findViewById(R.id.editText_Nascimento);
        campoAltura = findViewById(R.id.editText_Altura);
    }
}