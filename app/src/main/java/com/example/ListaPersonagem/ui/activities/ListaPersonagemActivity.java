package com.example.ListaPersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ListaPersonagem.dao.PersonagenDao;
import com.example.ListaPersonagem.model.Personagen;
import com.example.auladia11.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {
    private  final PersonagenDao dao = new PersonagenDao();
    @Override
    protected  void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista Personagens");
        dao.salva(new Personagen("Riu","10031970","1.80"));
        dao.salva(new Personagen("Ken","10031971","1.79"));


        botaoAddNovoPersonagem();
    }

    private void botaoAddNovoPersonagem() {
        FloatingActionButton BtNovoPersonagem = findViewById(R.id.fab_add);
        BtNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity( new Intent( ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });
    }

    @Override
    protected  void onResume()
    {
        super.onResume();



        ListView listadePersonagem = findViewById(R.id.activity_main_lista_personagem);
        List<Personagen> personagens = dao.todos();
        listadePersonagem.setAdapter(new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, personagens));

        listadePersonagem.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id)
            {
                Personagen personagenEscolhido = personagens.get(posicao);

                Intent vaiParaOfotmulario = new Intent(ListaPersonagemActivity.this,FormularioPersonagemActivity.class);
                vaiParaOfotmulario.putExtra("personagen",personagenEscolhido);
                startActivity(vaiParaOfotmulario);

            }
    }
    );
    }
}
