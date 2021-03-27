package com.example.ListaPersonagem.dao;

import com.example.ListaPersonagem.model.Personagen;

import java.util.ArrayList;
import  java.util.List;

public class PersonagenDao {

    private final static List<Personagen> personagens = new ArrayList<>();

    public void salva(Personagen personagemSalvo)
    {
          personagens.add(personagemSalvo);
    }

    public  List<Personagen> todos()
    {
         return  new    ArrayList<>(personagens);
    }
}
