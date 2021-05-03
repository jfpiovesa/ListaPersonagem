package com.example.ListaPersonagem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ListaPersonagem.dao.PersonagenDao;
import com.example.ListaPersonagem.model.Personagen;
import com.example.auladia11.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEN;
import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.TITULO_APPBAR_EDITA_PERSONAGEN;
import static com.example.ListaPersonagem.ui.activities.ConstantesActivities.TITULO_APPBAR_NOVO_PERSONAGEN;

public class FormularioPersonagemActivity extends AppCompatActivity {

    // variaveis para seren editar os texto referente a elas
    private EditText campoNome;
    private EditText campoNacimento;
    private EditText campoAltura;
    // variaveis para salvar  com referente seu nome
    private String nome;
    private String nascimento;
    private String altura;

    private final PersonagenDao dao = new PersonagenDao();
    private Personagen personagen;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) // criando botão salvar personagem
    {
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)// selecionando o botão criado
    {
        int itenId = item.getItemId();
        if (itenId == R.id.Activity_lista_personagens_menu_salvar) {
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)// metodo responsavel por criar o formulario dos personagens
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializacaoCampos();
        configuraBotaoSalvar();
        carregaPersonagen();

    }

    private void carregaPersonagen()  //metodo para carregar os dados do personagem salvo
    {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEN)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEN);
            personagen = (Personagen) dados.getSerializableExtra(CHAVE_PERSONAGEN);
            preencheCampos(personagen);
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEN);
            personagen = new Personagen();
        }
    }

    private void preencheCampos(Personagen personagen) // meto para setar  os dados dos  personagem
    {
        campoNome.setText(personagen.getNome());
        campoNacimento.setText(personagen.getNacimento());
        campoAltura.setText(personagen.getAltura());
    }

    private void configuraBotaoSalvar() {
        // Pegando  o botao de salvar, para por um listener de ações nele, no caso, salvar  informaçõa do personagem.
        Button btSalvar = findViewById(R.id.bt_salvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finalizarFormulario();
                                        }
                                    }
        );
    }

    private void finalizarFormulario() {
        prencherPersonagen();
        if (personagen.IdValido()) {
            dao.editar(personagen);
            finish();
        } else {
            dao.salva(personagen);
        }
        finish();
    }

    private void prencherPersonagen() // metodo que para pegar os dados foram salvos ou não e seta eles
    {


        nome = campoNome.getText().toString();
        nascimento = campoNacimento.getText().toString();
        altura = campoAltura.getText().toString();

        personagen.setNome(nome);
        personagen.setNacimento(nascimento);
        personagen.setAltura(altura);
    }

    private void inicializacaoCampos() // metodo Pegando os  ids referentes aos dados do personagem
    {
        campoNome = findViewById(R.id.editTextText_Name);
        campoNacimento = findViewById(R.id.editText_Nascimento);
        campoAltura = findViewById(R.id.editText_Altura);

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);
        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNacimento, smfNascimento);
        campoNacimento.addTextChangedListener(mtwNascimento);
    }
}