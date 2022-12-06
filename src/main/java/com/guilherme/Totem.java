package com.guilherme;

import com.guilherme.exceptions.AeroportoInexistenteException;
import com.guilherme.exceptions.AeroportosIguaisException;
import com.guilherme.exceptions.SenhaIncorretaException;

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
                    try {
                        imprimirListaDePassageiros();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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

    private static void imprimirListaDePassageiros()
    throws SenhaIncorretaException, AeroportoInexistenteException, AeroportosIguaisException {
        System.out.print("Digite a senha: ");
        String senhaDigitada = scanner.nextLine();

        if (!senhaDigitada.equals(senhaAdmin)) {
            throw new SenhaIncorretaException();
        }

        // Validação dos aeroportos
        System.out.print("Aeroporto de origem: ");
        String origem = scanner.nextLine();
        if (!aeroportos.contains(origem)) {
            throw new AeroportoInexistenteException(origem);
        }

        System.out.print("Aeroporto de destino: ");
        String destino = scanner.nextLine();
        if (!aeroportos.contains(destino)) {
            throw new AeroportoInexistenteException(destino);
        }

        if (origem.equals(destino)) {
            throw new AeroportosIguaisException();
        }

        // Busca pelo voo na lista voos
        Voo voo = buscarVoo(origem, destino);

        for (int i = 0; i < NUM_ASSENTOS; i++) {
            String passageiro;
            if (voo.getPassageiroEm(i) != null) {
                passageiro = voo.getPassageiroEm(i).getNome();
                System.out.printf("Passageiro na poltrona %d: %s", i + 1, passageiro);
            }
        }
    }

    private static Voo buscarVoo(String origem, String destino) {
        Voo voo = null;
        for (Voo vooIter : voos) {
            if (vooIter.getOrigem().equals(origem) &&
                    vooIter.getDestino().equals(destino)) {
                voo = vooIter;
                break;
            }
        }
        return voo;
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