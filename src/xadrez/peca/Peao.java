package xadrez.peca;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez{
	
	private PartidaDeXadrez partidaDeXadrez;
	

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}

	
	@Override
	public boolean[][] movimentosPossiveis() {
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao auxiliar = new Posicao(0, 0);
		
		
		if (getCor() == Cor.BRANCO) {
			
			// Mover
			auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() -  2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar) && 
					getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemDeMovimentos() == 0) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Capiturar
			auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			//Movimento especial En passant.
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && existPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getVulneravelEnPassant()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && existPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getVulneravelEnPassant()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
			
		} else {
			
			// Mover
			auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(auxiliar) && !getTabuleiro().temUmaPeca(auxiliar) && 
					getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemDeMovimentos() == 0) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			// Capiturar
			auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			auxiliar.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(auxiliar) && existPecaAdversaria(auxiliar)) {
				matriz[auxiliar.getLinha()][auxiliar.getColuna()] = true;
			}
			
			//Movimento especial En passant.
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && existPecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getVulneravelEnPassant()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && existPecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getVulneravelEnPassant()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}
}
