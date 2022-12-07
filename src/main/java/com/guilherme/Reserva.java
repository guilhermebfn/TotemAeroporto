package com.guilherme;

import java.time.LocalDateTime;

public class Reserva {

    private Voo voo;
    private int numAssento;
    private double valor;
    private final String codigoReserva;
    private final LocalDateTime dataHora;

    public Reserva(Voo voo, int numAssento, double valor, String codigoReserva) {
        this.voo = voo;
        this.numAssento = numAssento;
        this.valor = valor;
        this.codigoReserva = codigoReserva;
        this.dataHora = LocalDateTime.now();
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public int getNumAssento() {
        return numAssento;
    }

    public void setNumAssento(int numAssento) {
        this.numAssento = numAssento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
