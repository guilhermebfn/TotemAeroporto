package com.guilherme;

public class Passageiro {

    private String nome;
    private String cpf;
    private Reserva reserva;

    public Passageiro(String nome, String cpf, Reserva reserva) {
        this.nome = nome;
        this.cpf = cpf;
        this.reserva = reserva;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}