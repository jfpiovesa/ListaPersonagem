package com.example.ListaPersonagem.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ListaPersonagem.dao.PersonagenDao;
import com.example.ListaPersonagem.model.Personagen;
import com.example.auladia11.R;

import java.io.Serializable;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private   EditText campoNome;
    private   EditText campoNacimento;
    private   EditText campoAltura;

    private  final PersonagenDao dao = new PersonagenDao();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulario Personagens");



         campoNome = findViewById(R.id.editTextText_Name);
         campoNacimento = findViewById(R.id.editText_Nascimento);
         campoAltura = findViewById(R.id.editText_Altura);

        Button btSalvar =  findViewById(R.id.bt_salvar);
        btSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String nome = campoNome.getText().toString();
                String nascimento = campoNacimento.getText().toString();
                String altura = campoAltura.getText().toString();

                new Personagen(nome,nascimento,altura);


                Personagen personagemSalvo = new Personagen(nome,nascimento,altura);

                dao.salva(personagemSalvo);
                finish();

                ///startActivity(new Intent(FormularioPersonagemActivity.this,ListaPersonagemActivity.class));
                //Toast.makeText( FormularioPersonagemActivity.this, personagemSalvo.getNome() + "-" + personagemSalvo.getAltura()+"-"+ personagemSalvo.getNacimento() ,Toast.LENGTH_SHORT).show();
                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNacimento(nascimento);
                dao.editar(personagemSalvo);

               Intent dados = getIntent();
               Personagen ps = (Personagen) dados.getSerializableExtra("Personagen");
               campoNome.setText(ps.getNome());
               campoNacimento.setText(ps.getNacimento());
               campoAltura.setText(ps.getAltura());

            }
        }
        );
    }
}