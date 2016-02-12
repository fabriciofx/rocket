package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNumNeg<T extends Number> implements Validacao<T> {
	private final Validacao<T> validacao;

	public ValidacaoNumNeg() {
		this(new Validacao.Terminal<T>());
	}

	public ValidacaoNumNeg(final Validacao<T> restricao) {
		this.validacao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto.doubleValue() >= 0) {
			throw new IllegalArgumentException("número positivo");
		}

		validacao.valida(objeto);
	}
}
