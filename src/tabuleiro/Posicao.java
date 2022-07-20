package tabuleiro;

public class Posicao {
	
	private int linha;
	private int coluna;
	
	
	public Posicao(int linha, int coluno) {
		this.linha = linha;
		this.coluna = coluno;
	}

	
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	@Override
	public String toString() {
		return linha + ", " + coluna;
	}
	
}
