package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNumPos<T extends Number> implements Validacao<T> {
	private final Validacao<T> validacao;

	public ValidacaoNumPos() {
		this(new Validacao.Terminal<T>());
	}

	public ValidacaoNumPos(final Validacao<T> validacao) {
		this.validacao = validacao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto.doubleValue() < 0) {
			throw new IllegalArgumentException("número negativo");
		}

		validacao.valida(objeto);
	}
}
