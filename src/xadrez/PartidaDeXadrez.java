package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peca.Rei;
import xadrez.peca.Torre;

public class PartidaDeXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapituradas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		// A partida de xadrez é que deve saber as dimensões do tabuleiro.
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configuracaoInicial();
	}
	
	
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosicaoDeXadrez posicaoDeOrigem) {
		Posicao posicao = posicaoDeOrigem.posicionar();
		validarPosicaoInicial(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaDeXadrez executarMovimentoDeXadrez (PosicaoDeXadrez pInicial, PosicaoDeXadrez pFinal) {
		Posicao inicial = pInicial.posicionar();
		Posicao poFinal = pFinal.posicionar();
		
		validarPosicaoInicial(inicial);
		validarPosicaoAlvo(inicial, poFinal);
		Peca pecaCapiturada = realizarMovimento(inicial, poFinal);
		proximoTurno();
		return (PecaDeXadrez) pecaCapiturada;
	}

	private Peca realizarMovimento(Posicao inicial, Posicao poFinal) {
		Peca p = tabuleiro.removerPeca(inicial);
		Peca pecaCapiturada = tabuleiro.removerPeca(poFinal);
		
		if (pecaCapiturada != null) {
			pecasNoTabuleiro.remove(pecaCapiturada);
			pecasCapituradas.add(pecaCapiturada);
		}
		
		tabuleiro.colocarPeca(p, poFinal);
		return pecaCapiturada;
	}

	private void validarPosicaoInicial(Posicao inicial) {
		if (!tabuleiro.temUmaPeca(inicial)) {
			throw new XadrezException("Nao ha peca na posicao de origem!");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(inicial)).getCor()) {
			throw new XadrezException("A peca escolhida nao e sua!");
		}
		if (!tabuleiro.peca(inicial).existeAlgumMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida!");
		}
	}

	private void validarPosicaoAlvo(Posicao inicial, Posicao poFinal) {
		if (!tabuleiro.peca(inicial).movimentoPossivel(poFinal)) {
			throw  new XadrezException("A peca nao pode ser movida para a posicao escolhida!");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	public void configuracaoInicial() {
		colocarNovaPeca('b', 3, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 4, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 3, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 4, new Rei(tabuleiro, Cor.PRETO));
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoDeXadrez(coluna, linha).posicionar());
		pecasNoTabuleiro.add(peca);
	}
	
}
