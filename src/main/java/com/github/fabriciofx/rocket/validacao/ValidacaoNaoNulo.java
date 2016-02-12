package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNaoNulo<T> implements Restricao<T> {
	private final Restricao<T> restricao;

	public ValidacaoNaoNulo() {
		this(new Restricao.Terminal<T>());
	}
	
	public ValidacaoNaoNulo(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null) {
			throw new IllegalArgumentException("nulo");
		}
		
		restricao.valida(objeto);
	}
}
