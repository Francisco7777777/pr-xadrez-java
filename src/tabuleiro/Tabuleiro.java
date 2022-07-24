package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar o tabuleiro: "
					+ "E necessario que haja pelo menos 1 linha e uma coluna.\r\n");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		this.pecas = new Peca[linhas][colunas];
	}


	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	
	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Erro: Posicao nao existe! ");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Erro: Posição nao existe! ");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if (temUmaPeca(posicao)) {
			throw new TabuleiroException("Ja a uma peca na posicao! " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Erro: Posição não existe! ");
		}
		if (peca(posicao) == null) {
			return null;
		}
		
		// auxiliar recebe a posição peça no tabuleiro.
		Peca auxiliar = peca(posicao);
		auxiliar.posicao = null; 	 			// A peça foi retirada do tabuleiro.
				
		// Na posição do tabuleiro receberá null. Indicando que a peça foi retirada.  
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return auxiliar;
	}
	
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean temUmaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Erro: Posicao nao existe! ");
		}
		return peca(posicao) != null;
	}
}
