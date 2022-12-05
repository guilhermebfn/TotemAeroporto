package com.guilherme.exceptions;

public class AeroportosIguaisException extends Exception {

    public AeroportosIguaisException() {
        super("Aeroportos de origem e destino não podem ser iguais");
    }
}
