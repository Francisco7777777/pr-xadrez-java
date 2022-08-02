package xadrez.peca;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Cavalo extends PecaDeXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	
	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao auxiliar = new Posicao(0, 0);

		auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		auxiliar.definirValores(posicao.getLinha() - 2, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		auxiliar.definirValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		auxiliar.definirValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		auxiliar.definirValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(auxiliar) && podeMover(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		return matriz;
	}

	@Override
	public String toString() {
		return "C";
	}
}