package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNaoNulo<T> implements Validacao<T> {
	private final Validacao<T> validacao;

	public ValidacaoNaoNulo() {
		this(new Validacao.Terminal<T>());
	}

	public ValidacaoNaoNulo(final Validacao<T> validacao) {
		this.validacao = validacao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null) {
			throw new IllegalArgumentException("nulo");
		}

		validacao.valida(objeto);
	}
}
