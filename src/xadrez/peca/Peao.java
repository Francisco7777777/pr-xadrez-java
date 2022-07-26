package xadrez.peca;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao auxiliar = new Posicao(0, 0);
		
		
		if (getCor() == Cor.BRANCO) {
			
			// Mover
			auxiliar.definirValores(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() -2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar) && 
					getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemDeMovimentos() == 0) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Capiturar
			auxiliar.definirValores(posicao.getLinha() -1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() -1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
		} else {
			
			// Mover
			auxiliar.definirValores(posicao.getLinha() +1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() +2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar) && 
					getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemDeMovimentos() == 0) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Capiturar
			auxiliar.definirValores(posicao.getLinha() +1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() +1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
		}
		return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}
}
