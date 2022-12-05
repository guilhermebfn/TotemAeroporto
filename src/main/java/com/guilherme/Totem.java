package com.guilherme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Totem {

    private static final String senhaAdmin = "senhamestre";
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> aeroportos = new ArrayList<>(Arrays.asList(
            "Fortaleza",
            "São Paulo",
            "Salvador",
            "Brasília",
            "Manaus")
    );
    private static final List<Voo> voos = new ArrayList<>();
    private static final int NUM_ASSENTOS = 220;

    public static void main(String[] args) {
        inicializarVoos();
        int opcao = 0;

        while (opcao != 6) {
            imprimirMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    comprarPassagem();
                    break;

                case 2:
                    alterarAssento();
                    break;

                case 3:
                    alterarTitularidade();
                    break;

                case 4:
                    cancelarReserva();
                    break;

                case 5:
                    imprimirListaDePassageiros();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void comprarPassagem() {
    }

    private static void alterarAssento() {
    }

    private static void alterarTitularidade() {
    }

    private static void cancelarReserva() {
    }

    private static void inicializarVoos() {
        for (String origem : aeroportos) {
            for (String destino : aeroportos) {
                if (!origem.equals(destino)) {
                    voos.add(new Voo(origem, destino, NUM_ASSENTOS));
                }
            }
        }
    }

    private static void imprimirListaDePassageiros() {
    }

    private static void imprimirMenu() {
        System.out.println();
        System.out.println("1 - Comprar uma passagem");
        System.out.println("2 - Alterar o assento");
        System.out.println("3 - Alterar titularidade de uma reserva");
        System.out.println("4 - Cancelar uma reserva");
        System.out.println("5 - Imprimir lista de passageiros");
        System.out.println("6 - Sair");
        System.out.print("\nOpção escolhida: ");
    }
}