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
		
		while (true) {
			try {
				UI.limparTela();
				UI.ImprimirPartida(partidaDeXadrez, capituradas);
				System.out.println();
				System.out.print("P inicial: ");
				PosicaoDeXadrez inicial = UI.lerPosicaoDoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(inicial);
				UI.limparTela();
				UI.imprimirTabuleiro(partidaDeXadrez.obterPeca(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("P final: ");
				PosicaoDeXadrez  pfinal = UI.lerPosicaoDoXadrez(sc);
				
				PecaDeXadrez pecaCapiturada = partidaDeXadrez.executarMovimentoDeXadrez(inicial, pfinal);
				
				if (pecaCapiturada != null) {
					capituradas.add(pecaCapiturada);
				}
			
			} catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}