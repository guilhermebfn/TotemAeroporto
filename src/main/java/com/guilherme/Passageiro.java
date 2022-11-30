package com.guilherme;

public class Passageiro {

    private String nome;
    private String cpf;
    private int numAssento;
    private final Voo voo;

    public Passageiro(String nome, String cpf, int numAssento, Voo voo) {
        this.nome = nome;
        this.cpf = cpf;
        this.numAssento = numAssento;
        this.voo = voo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumAssento() {
        return numAssento;
    }

    public void setNumAssento(int numAssento) {
        this.numAssento = numAssento;
    }

    public Voo getVoo() {
        return voo;
    }
}