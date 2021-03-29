package com.example.ListaPersonagem.dao;

import com.example.ListaPersonagem.model.Personagen;

import java.util.ArrayList;
import  java.util.List;

public class PersonagenDao {

    private final static List<Personagen> personagens = new ArrayList<>();
    private  static int contadorDeId = 1;

    public void salva(Personagen personagemSalvo)
    {
        personagemSalvo.setId(contadorDeId);
          personagens.add(personagemSalvo);
          contadorDeId++;
    }
    public  void  editar(Personagen personagen)
    {
        Personagen  personagemEscolhido = null;
        for (Personagen p: personagens )
        {
            if (p.getId() == personagen.getId() )
            {
                personagemEscolhido = p;
            }
        }
        if (personagemEscolhido != null)
        {
            int posicaoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoPersonagem,personagen);
        }
    }

    public  List<Personagen> todos()
    {
         return  new    ArrayList<>(personagens);
    }
}
