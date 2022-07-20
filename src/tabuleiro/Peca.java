package tabuleiro;

public class Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		/* Inicialmente a pe�a � iniciada com a posi��o null, 
		 * pois ainda n�o foi posicionada no tabuleiro. */
	}


	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
}
