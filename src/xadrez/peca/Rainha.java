package xadrez.peca;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rainha extends PecaDeXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	
	@Override
	public boolean[][] movimentosPossiveis() {
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao auxiliar = new Posicao(0, 0);

		// Para assima.
		auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setLinha(auxiliar.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		// Para a esquerda.
		auxiliar.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setColuna(auxiliar.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		// Para a direita.
		auxiliar.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setColuna(auxiliar.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		// Para baixo.
		auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.setLinha(auxiliar.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		
		// Para NO.
		auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna() -1);
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.definirValores(auxiliar.getLinha() - 1, auxiliar.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		// Para NE.
		auxiliar.definirValores(posicao.getLinha() -1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.definirValores(auxiliar.getLinha() - 1, auxiliar.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		// Para SE.
		auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.definirValores(auxiliar.getLinha() + 1, auxiliar.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}

		// Para SO.
		auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			auxiliar.definirValores(auxiliar.getLinha() + 1, auxiliar.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
			matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
		}
		return matriz;
	}

	@Override
	public String toString() {
		return "Q";
	}
}
