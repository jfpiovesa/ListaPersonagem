package com.example.ListaPersonagem.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagen implements Serializable {
    private  String nome;
    private  String nacimento;
    private  String altura;
    private  int id = 0;


    public Personagen(String nome, String nascimento, String altura)
    {
         this.nome = nome;
         this.nacimento = nascimento;
         this.altura = altura;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacimento() {
        return nacimento;
    }

    public void setNacimento(String nacimento) {
        this.nacimento = nacimento;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    @NonNull
    @Override
    public String toString()
    {
        return  nome;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int  getId()
    {
       return id;
    }

}
