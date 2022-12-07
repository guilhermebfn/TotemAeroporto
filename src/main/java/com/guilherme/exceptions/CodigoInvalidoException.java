package com.guilherme.exceptions;

public class CodigoInvalidoException extends Exception {

    public CodigoInvalidoException() {
        super("Código de reserva inválido");
    }
}
