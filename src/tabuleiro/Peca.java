package tabuleiro;

public class Peca {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		/* Inicialmente a peça é iniciada com a posição null, 
		 * pois ainda não foi posicionada no tabuleiro. */
	}


	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
}
