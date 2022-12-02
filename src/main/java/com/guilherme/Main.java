package com.guilherme;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        imprimirMenu();
    }

    private static void imprimirMenu() {
        System.out.println("1 - Comprar uma passagem");
        System.out.println("2 - Alterar o assento");
        System.out.println("3 - Alterar titularidade de uma reserva");
        System.out.println("4 - Cancelar uma reserva");
        System.out.println("5 - Imprimir lista de passageiros");
    }
}