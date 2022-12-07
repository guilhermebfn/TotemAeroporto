package com.guilherme;

import com.guilherme.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Totem {

    private static final String senhaAdmin = "senhamestre";
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> aeroportos = new ArrayList<>(Arrays.asList(
            "FOR",
            "CGH",
            "SSA",
            "BSB",
            "MAO")
    );
    private static final List<Voo> voos = new ArrayList<>();
    private static final int NUM_ASSENTOS = 220;

    public static void main(String[] args) {
        inicializarVoos();
        loopPrincipal();
    }

    private static void loopPrincipal() {
        int opcao = 0;

        while (opcao != 6) {
            imprimirMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    try {
                        comprarPassagem();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        alterarAssento();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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

    private static void inicializarVoos() {
        for (String origem : aeroportos) {
            for (String destino : aeroportos) {
                if (!origem.equals(destino)) {
                    voos.add(new Voo(origem, destino, NUM_ASSENTOS));
                }
            }
        }
    }

    private static void comprarPassagem()
    throws AeroportoInexistenteException, AeroportosIguaisException, AssentoIndisponivelException {
        System.out.print("Aeroporto de origem: ");
        String origem = scanner.nextLine();
        System.out.print("Aeroporto de destino: ");
        String destino = scanner.nextLine();

        validarAeroportos(origem, destino);

        Voo voo = buscarVoo(origem, destino);

        // Confirmação
        double valorPassagem = calcularPassagem(voo);
        String confirmacao;
        do {
            System.out.printf("Valor da passagem: %.2f reais. Confirmar compra? (S/n)\n", valorPassagem);
            confirmacao = scanner.nextLine();
        } while (!confirmacao.equals("S") && !confirmacao.equals("n"));

        if (confirmacao.equals("n")) { return; }

        // Dados do passageiro
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        // Alocação do assento
        for (int assentoAtual = 6; assentoAtual < NUM_ASSENTOS; assentoAtual++) { // Começa em 6 devido a os seis primeiros assentos serem mais caros
            if (voo.getPassageiroEm(assentoAtual) == null) {
                String codigoReserva = gerarCodigoReserva(origem, destino, assentoAtual);
                var reserva = new Reserva(voo, assentoAtual, valorPassagem, codigoReserva);
                var passageiro = new Passageiro(nome, cpf, reserva);
                voo.setPassageiroEm(passageiro, assentoAtual);
                voo.incrementarValorTotal(valorPassagem);
                System.out.println("Assento alocado: " + (assentoAtual + 1));
                System.out.println("Código de reserva gerado: " + codigoReserva);
                return;
            }

        }

        // Se chegar ao último assento, e ele estiver indisponível, verifica se algum dos
        // seis assentos iniciais está disponível
        for (int assentoAtual = 0; assentoAtual < 6; assentoAtual++) {
            if (voo.getPassageiroEm(assentoAtual) == null) {
                String codigoReserva = gerarCodigoReserva(origem, destino, assentoAtual);
                var reserva = new Reserva(voo, assentoAtual, valorPassagem, codigoReserva);
                var passageiro = new Passageiro(nome, cpf, reserva);
                voo.setPassageiroEm(passageiro, assentoAtual);
                voo.incrementarValorTotal(valorPassagem);
                System.out.println("Assento alocado: " + (assentoAtual + 1));
                System.out.println("Código de reserva gerado: " + codigoReserva);
                return;
            }
        }

        // Se chegar aqui, é porque nenhum assento está disponível
        throw new AssentoIndisponivelException();
    }

    private static String gerarCodigoReserva(String origem, String destino, int assentoAtual) {
        return String.format("%s%s%03d", origem, destino, assentoAtual + 1);
    }

    private static double calcularPassagem(Voo voo) {
        int numeroDePassageiros = 0;
        for (int assentoAtual = 0; assentoAtual < NUM_ASSENTOS; assentoAtual++) {
            if (voo.getPassageiroEm(assentoAtual) != null) {
                numeroDePassageiros++;
            }
        }

        return 100 + Math.pow(5, Math.log10(numeroDePassageiros));
    }

    private static void alterarAssento()
            throws CodigoInvalidoException, AeroportoInexistenteException, AeroportosIguaisException, AssentoIndisponivelException, AssentoInexistenteException {
        System.out.print("Digite o código de reserva: ");
        String codigoReserva = scanner.nextLine();

        if (codigoReserva.length() != 9) {
            throw new CodigoInvalidoException();
        }

        String origem = extrairOrigem(codigoReserva);
        String destino = extrairDestino(codigoReserva);
        int assentoAntigo = extrairAssento(codigoReserva);

        validarAeroportos(origem, destino);

        Voo voo = buscarVoo(origem, destino);

        // Validação de que realmente há um passageiro naquele assento
        if (voo.getPassageiroEm(assentoAntigo) == null) {
            throw new CodigoInvalidoException();
        }

        System.out.print("Digite o novo assento em que você quer ficar: ");
        int assentoNovo = Integer.parseInt(scanner.nextLine()) - 1;  // O número de assento é usado como índice no vetor de passageiros, então precisa ser diminuído em 1

        if (assentoNovo < 0 || assentoNovo >= NUM_ASSENTOS) {
            throw new AssentoInexistenteException();
        }

        if (voo.getPassageiroEm(assentoNovo) != null) {
            throw new AssentoIndisponivelException();
        }

        Passageiro passageiro = voo.getPassageiroEm(assentoAntigo);

        // Valor adicional para os assentos iniciais
        if (assentoNovo < 6) {
            String confirmacao;
            do {
                System.out.println("O assento escolhido custa 50 reais a mais. Confirmar a troca? (S/n)");
                confirmacao = scanner.nextLine();
            } while (!confirmacao.equals("S") && !confirmacao.equals("n"));

            if (confirmacao.equals("n")) { return; }

            passageiro.getReserva().incrementarValor(50);
            voo.incrementarValorTotal(50);
        }

        voo.setPassageiroEm(null, assentoAntigo);
        voo.setPassageiroEm(passageiro, assentoNovo);
    }

    private static String extrairOrigem(String codigoReserva) {
        return codigoReserva.substring(0, 3);
    }

    private static String extrairDestino(String codigoReserva) {
        return codigoReserva.substring(3, 6);
    }

    private static int extrairAssento(String codigoReserva) {
        // O número de assento é usado como índice no vetor de passageiros, então precisa ser diminuído
        // em 1 do código de reserva que contém o valor de assento
        return Integer.parseInt(codigoReserva.substring(6, 9)) - 1;
    }

    private static void alterarTitularidade() {
    }

    private static void cancelarReserva() {
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
        System.out.print("Aeroporto de destino: ");
        String destino = scanner.nextLine();

        validarAeroportos(origem, destino);

        // Busca pelo voo na lista voos
        Voo voo = buscarVoo(origem, destino);

        for (int i = 0; i < NUM_ASSENTOS; i++) {
            Passageiro passageiro;
            if (voo.getPassageiroEm(i) != null) {
                passageiro = voo.getPassageiroEm(i);
                System.out.printf("Passageiro na poltrona %d: %s\n", i + 1, passageiro);
            }
        }

        System.out.println("Valor total das vendas de passagens: " + voo.getValorTotal());
    }

    private static void validarAeroportos(String origem, String destino)
    throws AeroportoInexistenteException, AeroportosIguaisException {
        if (!aeroportos.contains(origem)) {
            throw new AeroportoInexistenteException(origem);
        }

        if (!aeroportos.contains(destino)) {
            throw new AeroportoInexistenteException(destino);
        }

        if (origem.equals(destino)) {
            throw new AeroportosIguaisException();
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