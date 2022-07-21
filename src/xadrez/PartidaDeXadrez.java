package xadrez;

import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	
	public PartidaDeXadrez() {
		// A partida de xadrez é que deve saber as dimensões do tabuleiro.
		tabuleiro = new Tabuleiro(8, 8);
	}
	
	
	public PecaDeXadrez[][] obterPeca() {
		PecaDeXadrez[][] partida = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				partida[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return partida;
	}
	
}
