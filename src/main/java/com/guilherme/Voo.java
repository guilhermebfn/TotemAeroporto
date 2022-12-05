package com.guilherme;

import java.util.Objects;

public class Voo {

    private final String origem;
    private final String destino;
    private final Passageiro[] passageiros;

    public Voo(String origem, String destino, int numAssentos) {
        this.origem = origem;
        this.destino = destino;
        this.passageiros = new Passageiro[numAssentos];
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public Passageiro getPassageiroEm(int numAssento) {
        return passageiros[numAssento];
    }

    public void setPassageiroEm(Passageiro passageiro) {
        int numAssento = passageiro.getNumAssento();
        passageiros[numAssento - 1] = passageiro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voo voo = (Voo) o;
        return origem.equals(voo.origem) && destino.equals(voo.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origem, destino);
    }
}