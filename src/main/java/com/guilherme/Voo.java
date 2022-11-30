package com.guilherme;

public class Voo {

    private final String origem;
    private final String destino;
    private Passageiro[] passageiros;

    public Voo(String origem, String destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public Passageiro getPassageiroEm(int numAssento) {
        return passageiros[numAssento - 1];
    }

    public void setPassageiroEm(Passageiro passageiro) {
        int numAssento = passageiro.getNumAssento();
        passageiros[numAssento - 1] = passageiro;
    }
}