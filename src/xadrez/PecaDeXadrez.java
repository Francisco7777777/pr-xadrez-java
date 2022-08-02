package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{
	
	private Cor cor;
	
	private int contagemDeMovimentos;
	
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	
	public Cor getCor() {
		return cor;
	}
	
	public int getContagemDeMovimentos() {
		return contagemDeMovimentos;
	}
	
	
	public void incrementarContagemDeMovimentos() {
		contagemDeMovimentos ++;
	}
	
	public void decrementarContagemDeMovimentos() {
		contagemDeMovimentos --;
	}
	
	protected boolean existPecaAdversaria(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
	
	public PosicaoDeXadrez obterPosicaoNoXadrez() {
		return PosicaoDeXadrez.daPosicao(posicao);
	}
}