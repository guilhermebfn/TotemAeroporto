package com.guilherme;

public class Reserva {

    private Voo voo;
    private int numAssento;
    private double valor;
    private final String codigoReserva;

    public Reserva(Voo voo, int numAssento, double valor, String codigoReserva) {
        this.voo = voo;
        this.numAssento = numAssento;
        this.valor = valor;
        this.codigoReserva = codigoReserva;
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
}
