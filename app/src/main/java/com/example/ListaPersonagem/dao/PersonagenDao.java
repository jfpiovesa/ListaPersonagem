package com.example.ListaPersonagem.dao;

import com.example.ListaPersonagem.model.Personagen;

import java.util.ArrayList;
import java.util.List;

public class PersonagenDao {

    private final static List<Personagen> personagens = new ArrayList<>();// lista de personagens que seram criados
    private static int contadorDeId = 1;// valor dado ao personagem criado para que ele tenha uma id

    public void salva(Personagen personagemSalvo)//metodo para salvar dados do personagem
    {
        personagemSalvo.setId(contadorDeId);
        personagens.add(personagemSalvo);
        atualizaId();
    }

    private void atualizaId() {
        contadorDeId++;
    }

    public void editar(Personagen personagen)//metoro para poder editar dados do personagem
    {
        Personagen personagemEscolhido = BuscaPersonagenId(personagen);
        if (personagemEscolhido != null) {
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem, personagen);
        }
    }

    private Personagen BuscaPersonagenId(Personagen personagen)// metodo que verifica a id do personagem e busca id referente ao personagem
    {

        for (Personagen p : personagens) {
            if (p.getId() == personagen.getId()) {
                return p;
            }
        }
        return null;
    }

    public List<Personagen> todos() {
        return new ArrayList<>(personagens);
    }

    public void remover(Personagen personagemescolhido)// metodo para remover personagem da lista quando criado pra que não de erro de voltar
    {
        personagens.remove(personagemescolhido);

    }
}
