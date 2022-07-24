package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.TabuleiroException;
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
	
	public PecaDeXadrez executarMovimentoDeXadrez (PosicaoDeXadrez pInicial, PosicaoDeXadrez pFinal) {
		Posicao inicial = pInicial.posicionar();
		Posicao pofinal = pFinal.posicionar();
		
		validarPosicaoInicial(inicial);
		Peca pecaCapiturada = FazerMover(inicial, pofinal);
		return (PecaDeXadrez) pecaCapiturada;
	}

	private Peca FazerMover(Posicao inicial, Posicao pofinal) {
		Peca p = tabuleiro.removerPeca(inicial);
		Peca pecaCapiturada = tabuleiro.removerPeca(pofinal);
		tabuleiro.colocarPeca(p, pofinal);
		return pecaCapiturada;
	}

	private void validarPosicaoInicial(Posicao inicial) {
		if (!tabuleiro.temUmaPeca(inicial)) {
			throw new TabuleiroException("Não há peça na posição de origem");
		}
		if (!tabuleiro.peca(inicial).existeAlgumMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
		}
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
