package com.calegario.a1testsolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.JOptionPane;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean desejaSair = false;

        List<Integer> baralho = solicitarCartasAoUsuario();
        solicitarComandoParaContinuar(scanner);

        while (!desejaSair) {
            
            disputarJogo(baralho);
            desejaSair = perguntarSeDesejaSair(scanner);
        }

        scanner.close();
    }

    private static List<Integer> solicitarCartasAoUsuario() {
        List<Integer> baralho;
        baralho = new ArrayList<>();
        int novaCarta;
        String entradaDoUsuario;

        for (int i = 0; i < 24; i++) {
            entradaDoUsuario = JOptionPane.showInputDialog(
                    "Digite um número inteiro:"
            );
            novaCarta = Integer.parseInt(entradaDoUsuario);
            baralho.add(novaCarta);
        }

        return baralho;
    }

    private static void solicitarComandoParaContinuar(Scanner scanner) {
        String entradaDoUsuario;
        char charactereInserido;
        boolean deveContinuar = false;

        while (!deveContinuar) {
            System.out.print("Pressione \"C\" para continuar: ");
            entradaDoUsuario = scanner.next();
            charactereInserido = entradaDoUsuario.charAt(0);

            deveContinuar = (charactereInserido == 'C');
        }
    }

    private static void disputarJogo(List<Integer> baralho) {
        ServicoDeDealer dealer = new ServicoDeDealer(baralho);
        dealer.embaralhar();
        ConjuntoDeMaos maos = dealer.dividirBaralho();

        List<Integer> maoDoJogador1 = maos.getMao1();
        Jogador jogador1 = new Jogador(maoDoJogador1);

        List<Integer> maoDoJogador2 = maos.getMao2();
        Jogador jogador2 = new Jogador(maoDoJogador2);

        Jogo jogo = new Jogo(jogador1, jogador2);
        jogo.disputar();

        String resultado = ("""
                Pontuacao
                Jogador 1: """ + jogador1.mostrarPontuacao() + "\n"
                + "Jogador 2: " + jogador2.mostrarPontuacao());

        JOptionPane.showMessageDialog(null, resultado);
    }

    private static boolean perguntarSeDesejaSair(Scanner scanner) {
        System.out.println("Pressione \"S\" para sair ou qualquer outra tecla para jogar novamente.");
        String entradaDoUsuario = scanner.next();
        char caractereInserido = entradaDoUsuario.charAt(0);
        
        return caractereInserido == 'S';
    }
}

class ServicoDeDealer {

    private final List<Integer> baralho;

    public ServicoDeDealer(List<Integer> baralho) {
        this.baralho = baralho;
    }

    public ServicoDeDealer() {
        Integer[] matrizDeCartas = new Integer[]{
            33, 22, 7, 1, 3, 3, 3, 3, 8, 9, 21, 2, 12, 13, 14, 15, 9, 10,
            11, 12, 11, 13, 14, 13
        };

        this.baralho = Arrays.asList(matrizDeCartas);
    }

    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    public ConjuntoDeMaos dividirBaralho() {
        List<Integer> mao1 = particionarBaralho(0, 11);
        List<Integer> mao2 = particionarBaralho(12, 23);

        return new ConjuntoDeMaos(mao1, mao2);
    }

    private List<Integer> particionarBaralho(
            int primeiroIndice, int ultimoIndice) {

        List<Integer> particao = new ArrayList<>();
        int itemAtual;

        for (int indiceAtual = primeiroIndice; indiceAtual <= ultimoIndice; indiceAtual++) {
            itemAtual = baralho.get(indiceAtual);
            particao.add(itemAtual);
        }

        return particao;
    }
}

class ConjuntoDeMaos {

    public List<Integer> mao1;
    public List<Integer> mao2;

    public ConjuntoDeMaos(List<Integer> mao1, List<Integer> mao2) {
        this.mao1 = mao1;
        this.mao2 = mao2;
    }

    public List<Integer> getMao1() {
        return mao1;
    }

    public List<Integer> getMao2() {
        return mao2;
    }

}

class Jogador {

    private final Queue mao;
    private int pontuacao = 0;
    private boolean ganhouNaRodadaAnterior = false;

    public Jogador(Collection cartas) {
        int numeroDeCartas = cartas.size();

        this.mao = new ArrayBlockingQueue(
                numeroDeCartas,
                false,
                cartas);
    }

    public void aumentarPontuacao() {
        pontuacao++;
    }

    public void aumentarPontuacao(int quantidadeDePontos) {
        pontuacao += quantidadeDePontos;
    }

    public void informarSeGanhouNaRodadaAnterior(boolean ganhouNaRodadaAnterior) {
        this.ganhouNaRodadaAnterior = ganhouNaRodadaAnterior;
    }

    public boolean mostrarSeGanhouNaRodadaAnterior() {
        return ganhouNaRodadaAnterior;
    }

    public int mostrarCarta() {
        int proximaCarta = (int) mao.poll();

        return proximaCarta;
    }

    public int mostrarPontuacao() {
        return pontuacao;
    }
}

class Jogo {

    private final Jogador jogador1;
    private final Jogador jogador2;

    public Jogo(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    public void disputar() {
        for (int numeroDaRodada = 0; numeroDaRodada < 12; numeroDaRodada++) {
            disputarRodada();
        }
    }

    private void disputarRodada() {
        int cartaDoJogador1 = jogador1.mostrarCarta();
        int cartaDoJogador2 = jogador2.mostrarCarta();
        boolean aCarta1EMenorQueACarta2 = cartaDoJogador1 < cartaDoJogador2;
        boolean aCarta1EMaiorQueACarta2 = cartaDoJogador1 > cartaDoJogador2;
        boolean asCartasSaoIguais = cartaDoJogador1 == cartaDoJogador2;

        conferirVitoriaDoJogador1(aCarta1EMenorQueACarta2);
        conferirVitoriaDoJogador2(aCarta1EMaiorQueACarta2);
        conferirEmpate(asCartasSaoIguais);
    }

    private void conferirVitoriaDoJogador1(boolean resultado) {
        if (resultado) {
            jogador1.aumentarPontuacao();
        }
    }

    private void conferirVitoriaDoJogador2(boolean resultado) {
        if (resultado) {
            jogador2.aumentarPontuacao();
        }
    }

    private void conferirEmpate(boolean asCartasSaoIguais) {
        if (asCartasSaoIguais) {
            pontuarAqueleQueGanhouNaRodadaAnterior();
        }
    }

    private void pontuarAqueleQueGanhouNaRodadaAnterior() {
        boolean jogador1GanhouNaRodadaAnterior = jogador1.mostrarSeGanhouNaRodadaAnterior();

        if (jogador1GanhouNaRodadaAnterior) {
            jogador1.aumentarPontuacao(2);
            return;
        }

        jogador2.aumentarPontuacao(2);
    }
}
