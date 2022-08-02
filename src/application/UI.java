package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDeXadrez;

public class UI {
	
	// Código de cores.
	public static final String REDEFINIR = "\u001B[0m";
	public static final String PRETO = "\u001B[30m";
	public static final String VERMELHO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARELO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String ROXO = "\u001B[35m";
	public static final String CIANO = "\u001B[36m";
	public static final String BRANCO = "\u001B[37m";

	public static final String FUNDO_PRETO = "\u001B[40m";
	public static final String FUNDO_VERMELHO = "\u001B[41m";
	public static final String FUNDO_VERDE = "\u001B[42m";
	public static final String FUNDO_AMARELO = "\u001B[43m";
	public static final String FUNDO_AZUL = "\u001B[44m";
	public static final String FUNDO_ROXO = "\u001B[45m";
	public static final String FUNDO_CIANO = "\u001B[46m";
	public static final String FUNDO_BRANCO = "\u001B[47m";
	
	
	public static PosicaoDeXadrez lerPosicaoDoXadrez(Scanner entrada) {
		try {
			String s = entrada.nextLine().toLowerCase();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoDeXadrez(coluna, linha);
		} 
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo a posicao no xadrez: Valores validos sao de A1 ate H8");
		}
	} 
	
	public static void ImprimirPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> capituradas) {
		
		imprimirTabuleiro(partidaDeXadrez.pegarPeca());
		System.out.println();
		imprimirPecaCapitulada(capituradas);
		System.out.println();
		System.out.println("Turno: " + partidaDeXadrez.getTurno());
		
		if (!partidaDeXadrez.getCheckMate()) {
			System.out.println("Esperando o jogador: " + partidaDeXadrez.getJogadorAtual());
			if (partidaDeXadrez.getCheck()) {
				System.out.println();
				System.out.println(AMARELO +" CHECK!! " + REDEFINIR);
			}
		} else {
			System.out.println();
			System.out.println(FUNDO_CIANO + VERMELHO + "CHECKMATE!!!");
			System.out.println("Vencedor: " + partidaDeXadrez.getJogadorAtual() + REDEFINIR);
		}
	}
	
	public static void imprimirTabuleiro(PecaDeXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			
			System.out.print(CIANO + (8 - i) + REDEFINIR + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println(CIANO + "  A B C D E F G H" + REDEFINIR);
	}
	
	
	public static void imprimirTabuleiro(PecaDeXadrez[][] pecas, boolean [][] movimetosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			
			System.out.print(CIANO + (8 - i) + REDEFINIR + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j], movimetosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println(CIANO + "  A B C D E F G H" + REDEFINIR);
	}
	
	
	private static void imprimirPeca(PecaDeXadrez peca, boolean fundo) {
		if (fundo) {
			System.out.print(FUNDO_AZUL);
		}
		if (peca == null) {
			System.out.print("-" + REDEFINIR);
		
		} else {
	        if (peca.getCor() == Cor.BRANCO) {
	            System.out.print(BRANCO + peca + REDEFINIR);
	        }
	        else {
	            System.out.print(VERDE + peca + REDEFINIR);
	        }
	    }
		System.out.print(" ");
	}
	
	public static void limparTela() { 
		 System.out.print("\033[H\033[2J"); 
		 System.out.flush(); 
	} 
	
	private static void imprimirPecaCapitulada(List<PecaDeXadrez> capiturada) {
		List<PecaDeXadrez> brancas = capiturada.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaDeXadrez> pretas = capiturada.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		
		System.out.println("PECA CAPITURADAS: ");
		System.out.print("Brancas ");
		System.out.print(BRANCO);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(REDEFINIR);
		
		System.out.print("Pretas ");
		System.out.print(VERDE);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(REDEFINIR);
	}
}
