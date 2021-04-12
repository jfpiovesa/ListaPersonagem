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

import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEN;
import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.TITULO_APPBAR_LISTA_PERSONAGENS;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagenDao dao = new PersonagenDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR_LISTA_PERSONAGENS);

        ConfiguraFabNovoPersonagem();
    }

    private void ConfiguraFabNovoPersonagem() {
        FloatingActionButton BtNovoPersonagem = findViewById(R.id.fab_add);
        BtNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AbreFormulario();
            }
        });
    }

    private void AbreFormulario() {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();


        ListView listadePersonagem = findViewById(R.id.activity_main_lista_personagem);
        final List<Personagen> personagens = dao.todos();
        listaDePersonagens(listadePersonagem, personagens);

        ConfiguraItenPorClique(listadePersonagem, personagens);
    }

    private void ConfiguraItenPorClique(ListView listadePersonagem, List<Personagen> personagens) {
        listadePersonagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                     @Override
                                                     public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                                                         Personagen personagenEscolhido = (Personagen) adapterView.getItemAtPosition(posicao);

                                                         AbreFormularioEditar(personagenEscolhido);

                                                     }
                                                 }
        );
    }

    private void AbreFormularioEditar(Personagen personagenEscolhido) {
        Intent vaiParaOfotmulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        vaiParaOfotmulario.putExtra(CHAVE_PERSONAGEN, personagenEscolhido);
        startActivity(vaiParaOfotmulario);
    }

    private void listaDePersonagens(ListView listadePersonagem, List<Personagen> personagens) {
        listadePersonagem.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }
}
