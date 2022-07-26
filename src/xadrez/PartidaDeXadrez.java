package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peca.Rei;
import xadrez.peca.Torre;
import xadrez.peca.Bispo;
import xadrez.peca.Cavalo;
import xadrez.peca.Peao;
import xadrez.peca.Rainha;

public class PartidaDeXadrez {
	
	private int turno;
	private boolean check;
	private boolean checkMate;
	
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private PecaDeXadrez vulneravelEnPassant;
	
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
	
	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PecaDeXadrez  getVulneravelEnPassant() {
		return vulneravelEnPassant;
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
		
		if (testarCheck(jogadorAtual)) {
			desfazerMovimento(inicial, poFinal, pecaCapiturada);
			throw new XadrezException("Movimento invalido, o Rei nao pode ser colocado em check!");
		}
		
		PecaDeXadrez pecaMovida = (PecaDeXadrez) tabuleiro.peca(poFinal);
		
		check = (testarCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testarCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {
			proximoTurno();
		}
		
		// Tratando o movimento especial en passant.
		if (pecaMovida instanceof Peao && (poFinal.getLinha() == inicial.getLinha() - 2 || poFinal.getLinha() == inicial.getLinha() + 2)) {
			vulneravelEnPassant = pecaMovida;
		} else {
			vulneravelEnPassant = null;
		}
		return (PecaDeXadrez) pecaCapiturada;
	}

	private Peca realizarMovimento(Posicao inicial, Posicao poFinal) {
	
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(inicial);
		p.incrementarContagemDeMovimentos();
		Peca pecaCapiturada = tabuleiro.removerPeca(poFinal);
		tabuleiro.colocarPeca(p, poFinal);
		
		if (pecaCapiturada != null) {
			pecasNoTabuleiro.remove(pecaCapiturada);
			pecasCapituradas.add(pecaCapiturada);
		}
		
		// Movimento do roque pequeno.
		if (p instanceof Rei && poFinal.getColuna() == inicial.getColuna() + 2) {
			Posicao posInicialTorre = new Posicao(inicial.getLinha(), inicial.getColuna() + 3);
			Posicao posFinalTorre = new Posicao(inicial.getLinha(), inicial.getColuna() + 1);
			
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(posInicialTorre);
			tabuleiro.colocarPeca(torre, posFinalTorre);
			torre.incrementarContagemDeMovimentos();
		}
		
		// Movimento do roque grande.
		if (p instanceof Rei && poFinal.getColuna() == inicial.getColuna() - 2) {
			Posicao posInicialTorre = new Posicao(inicial.getLinha(), inicial.getColuna() - 4);
			Posicao posFinalTorre = new Posicao(inicial.getLinha(), inicial.getColuna() - 1);
			
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(posInicialTorre);
			tabuleiro.colocarPeca(torre, posFinalTorre);
			torre.incrementarContagemDeMovimentos();
		}
		
		// Movimento do enpassant.
		if (p instanceof Peao) {
			if (inicial.getColuna() != poFinal.getColuna() && pecaCapiturada == null) {
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(poFinal.getLinha() + 1, poFinal.getColuna());
				} else {
					posicaoPeao = new Posicao(poFinal.getLinha() - 1, poFinal.getColuna());
				}
				pecaCapiturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapituradas.add(pecaCapiturada);
				pecasNoTabuleiro.remove(pecaCapiturada);
			}
		}
		return pecaCapiturada;
	}

	private void desfazerMovimento(Posicao pInicial, Posicao pFinal, Peca pecaCapiturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(pFinal);
		p.decrementarContagemDeMovimentos();
		tabuleiro.colocarPeca(p, pInicial);
		
		if (pecaCapiturada != null) {
			tabuleiro.colocarPeca(pecaCapiturada, pFinal);
			pecasCapituradas.remove(pecaCapiturada);
			pecasNoTabuleiro.add(pecaCapiturada);
		}
		
		// desfazer do roque pequeno.
		if (p instanceof Rei && pFinal.getColuna() == pInicial.getColuna() + 2) {
			Posicao posInicialTorre = new Posicao(pInicial.getLinha(), pInicial.getColuna() + 3);
			Posicao posFinalTorre = new Posicao(pInicial.getLinha(), pInicial.getColuna() + 1);
			
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(posFinalTorre);
			tabuleiro.colocarPeca(torre, posInicialTorre);
			torre.incrementarContagemDeMovimentos();
		}
		
		// Desfazer do roque grande.
		if (p instanceof Rei && pFinal.getColuna() == pInicial.getColuna() - 2) {
			Posicao posInicialTorre = new Posicao(pInicial.getLinha(), pInicial.getColuna() - 4);
			Posicao posFinalTorre = new Posicao(pInicial.getLinha(), pInicial.getColuna() - 1);
			
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(posFinalTorre);
			tabuleiro.colocarPeca(torre, posInicialTorre);
			torre.decrementarContagemDeMovimentos();;
		}
		
		// Desfazer movimento do en passant.
		if (p instanceof Peao) {
			if (pInicial.getColuna() != pFinal.getColuna() && pecaCapiturada == vulneravelEnPassant) {
				PecaDeXadrez peao = (PecaDeXadrez) tabuleiro.removerPeca(pFinal);
				Posicao posicaoPeao;
				if (p.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(3, pFinal.getColuna());
				} else {
					posicaoPeao = new Posicao(4, pFinal.getColuna());
				}
				tabuleiro.colocarPeca(peao, posicaoPeao);
			}
		}
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
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.BRANCO : Cor.PRETO;
	}
	
	private PecaDeXadrez Rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe rei da cor " + cor + " no tabuleiro!");
	}
	
	private boolean testarCheck(Cor cor) {
		Posicao posicaoDoRei = Rei(cor).obterPosicaoNoXadrez().posicionar();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		
		for (Peca p : pecasOponente) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testarCheckMate(Cor cor) {
		if (!testarCheck(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		
		for (Peca p : lista) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if(matriz[i][j]) {
						Posicao inicial = ((PecaDeXadrez) p).obterPosicaoNoXadrez().posicionar();
						Posicao pFinal = new Posicao(i, j);
						Peca pecaCapiturada = realizarMovimento(inicial, pFinal);
						
						boolean testecheck = testarCheck(cor);
						desfazerMovimento(inicial, pFinal, pecaCapiturada);
						if (!testecheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public void configuracaoInicial() {

		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoDeXadrez(coluna, linha).posicionar());
		pecasNoTabuleiro.add(peca);
	}
	
}
