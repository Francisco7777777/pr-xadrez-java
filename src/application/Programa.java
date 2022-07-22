package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDeXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				UI.limparTela();
				UI.imprimirTabuleiro(partidaDeXadrez.obterPeca());
				System.out.println();
				System.out.print("P inicial: ");
				PosicaoDeXadrez inicial = UI.lerPosicaoDoXadrez(sc);
				
				System.out.println();
				System.out.print("P final: ");
				PosicaoDeXadrez  pfinal = UI.lerPosicaoDoXadrez(sc);
				
				PecaDeXadrez pecaCapiturada = partidaDeXadrez.executarMovimentoDeXadrez(inicial, pfinal);
			
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