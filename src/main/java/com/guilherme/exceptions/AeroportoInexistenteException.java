package com.guilherme.exceptions;

public class AeroportoInexistenteException extends Exception {

    public AeroportoInexistenteException(String aeroporto) {
        super("Aeroporto inexistente: " + aeroporto);
    }
}
