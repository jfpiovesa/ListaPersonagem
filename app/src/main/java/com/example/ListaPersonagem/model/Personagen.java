package com.example.ListaPersonagem.model;

import androidx.annotation.NonNull;

public class Personagen {
    private final String nome;
    private final String nacimento;
    private final String altura;


    public Personagen(String nome, String nascimento, String altura)
    {
         this.nome = nome;
         this.nacimento = nascimento;
         this.altura = altura;

    }
    @NonNull
    @Override
    public String toString()
    {
        return  nome;
    }
//    public String getNome() {
//        return nome;
//    }
//
//    public String getNacimento() {
//        return nacimento;
//    }
//
//    public String getAltura() {
//        return altura;
//    }
}
