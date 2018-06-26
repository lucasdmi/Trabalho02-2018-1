package br.edu.iff.pooa20181.trabalho02_2018_1.model;

import java.util.Date;

public class Eleitor {

    private int id;


    private String nome;
    private String nomeMae;
    private Date dataNascimento;

    public Eleitor(){

    }

    public Eleitor(int id, String nome, String nomeMae, Date dataNascimento){
        this.id = id;
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.dataNascimento = dataNascimento;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

