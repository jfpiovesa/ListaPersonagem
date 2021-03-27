package com.example.ListaPersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.ListaPersonagem.dao.PersonagenDao;
import com.example.ListaPersonagem.model.Personagen;
import com.example.auladia11.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {
    @Override
    protected  void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("lista Personagens");



        FloatingActionButton BtNovoPersonagem = findViewById(R.id.fab_add);
        BtNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

       // List<String> personagem = new ArrayList<>(Arrays.asList("Alex","Ken","Ryu","Gui"));





    }
    @Override
    protected  void onResume()
    {
        super.onResume();

        PersonagenDao dao = new PersonagenDao();

        ListView listadePersonagem = findViewById(R.id.activity_main_lista_personagem);
        List<Personagen> personagens = dao.todos();
        listadePersonagem.setAdapter(new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, personagens));

        listadePersonagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id)
            {
                Personagen personagenEscolhido = personagens.get(posicao);

                Log.i("posicao", "" + posicao);

               Intent vaiParaOfotmulario = new Intent(ListaPersonagemActivity.this,FormularioPersonagemActivity.class);
               startActivity(vaiParaOfotmulario);

            }
        });




    }
}
