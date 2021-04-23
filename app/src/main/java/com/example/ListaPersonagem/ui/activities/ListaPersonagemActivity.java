package com.example.ListaPersonagem.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ListaPersonagem.dao.PersonagenDao;
import com.example.ListaPersonagem.model.Personagen;
import com.example.auladia11.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEN;
import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.TITULO_APPBAR_LISTA_PERSONAGENS;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagenDao dao = new PersonagenDao();
    private ArrayAdapter<Personagen> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)// reponsavel pela criação da lista de personagens
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR_LISTA_PERSONAGENS);

        ConfiguraFabNovoPersonagem();
        configuraLista();


    }

    private void ConfiguraFabNovoPersonagem()// metodo do botão que abre o formulario para que crie um personagem que vai ser adicionado na lista
    {
        FloatingActionButton BtNovoPersonagem = findViewById(R.id.fab_add);
        BtNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AbreFormulario();
            }
        });
    }

    private void AbreFormulario() // metodo que abre o formulario paara criar personagem
    {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        atualizaPersonagem();
    }

    private void atualizaPersonagem() // metodo que limpa a lista com adapter para não ter residuo de dados de personagens anteriores  e adiciona personagem
    {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)// adiciona um botão quando e  apertado sobre um personagem
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        // menu.add("Remover");
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) //
    {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }

    private void configuraMenu(@NonNull MenuItem item)
    {
        int itenID = item.getItemId();
        if (itenID == R.id.activity_lista_personagem_menu_remover) //se o iten for selecionado no caso personagem  e dada a opção de remover ou não da lista
        {

            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem  certeza  que deseja  remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int i)
                        {// remove o personagem da lista
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagen personagemescolhido = adapter.getItem(menuInfo.position);
                            remover(personagemescolhido);
                        }
                    }
                    )
                    .setNegativeButton("Não", null)
                    .show();// faz aparecer o menu com as opções comfiguradas acima
        }
    }

    private void remover(Personagen personagemescolhido)// metodo que remove o personagem
    {
        dao.remover(personagemescolhido);
        adapter.remove(personagemescolhido);
    }

    private void configuraLista()
    {
        ListView listaPersonagens = findViewById(R.id.activity_main_lista_personagem);
        // final List<Personagen> personagens = dao.todos();
        listaPersonagens(listaPersonagens);
        ConfiguraItenPorClique(listaPersonagens);
        registerForContextMenu(listaPersonagens);
    }

    private void ConfiguraItenPorClique(ListView listaPersonagens)//item que se clicado abre formulario de edição de personagem
    {
        listaPersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                                                        Personagen personagenEscolhido = (Personagen) adapterView.getItemAtPosition(posicao);

                                                        AbreFormularioEditar(personagenEscolhido);

                                                    }
                                                }
        );
    }

    private void AbreFormularioEditar(Personagen personagenEscolhido)//abre formulario pra edição
    {
        Intent vaiParaOfotmulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        vaiParaOfotmulario.putExtra(CHAVE_PERSONAGEN, personagenEscolhido);
        startActivity(vaiParaOfotmulario);
    }

    private void listaPersonagens(ListView listaPersonagens)// faz personagem da lista ficar visivel setando na lista
    {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaPersonagens.setAdapter(adapter);
    }
}
