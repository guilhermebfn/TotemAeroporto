package com.guilherme.exceptions;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("Senha incorreta");
    }
}
