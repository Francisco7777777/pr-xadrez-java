package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.peca.Rei;
import xadrez.peca.Torre;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;
	
	
	public PartidaDeXadrez() {
		// A partida de xadrez é que deve saber as dimensões do tabuleiro.
		tabuleiro = new Tabuleiro(8, 8);
		configuracaoInicial();
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

	public void configuracaoInicial() {
		colocarNovaPeca('b', 3, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 4, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 3, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 4, new Rei(tabuleiro, Cor.PRETO));
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoDeXadrez(coluna, linha).posicionar());
	}
	
}
