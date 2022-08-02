package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDeXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capituradas = new ArrayList<>();
		
		while (!partidaDeXadrez.getCheckMate()) {
			try {
				UI.limparTela();
				UI.ImprimirPartida(partidaDeXadrez, capituradas);
				System.out.println();
				System.out.print("P inicial: ");
				PosicaoDeXadrez inicial = UI.lerPosicaoDoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(inicial);
				UI.limparTela();
				UI.imprimirTabuleiro(partidaDeXadrez.pegarPeca(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("P final: ");
				PosicaoDeXadrez  pfinal = UI.lerPosicaoDoXadrez(sc);
				
				PecaDeXadrez pecaCapiturada = partidaDeXadrez.executarMovimentoDeXadrez(inicial, pfinal);
				
				if (pecaCapiturada != null) {
					capituradas.add(pecaCapiturada);
				}
				
				if (partidaDeXadrez.getPromovida() != null) {
					System.out.print("Insira a peca para promocao (B/C/T/Q): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
						System.out.print("Valor inválido! Insira novanente a peca para promocao (B/C/T/Q): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaDeXadrez.substituirPecaPromovida(tipo);
				}
			
			} catch (XadrezException e) {
				System.out.println(UI.VERMELHO + e.getMessage() + UI.REDEFINIR);
				sc.nextLine();
			
			} catch (InputMismatchException e) {
				System.out.println(UI.VERMELHO + e.getMessage() + UI.REDEFINIR);
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.ImprimirPartida(partidaDeXadrez, capituradas);
	}
}