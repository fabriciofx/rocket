package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoRegEx<T> implements Validacao<T> {
	private final Validacao<T> validacao;
	private final String regEx;

	public ValidacaoRegEx() {
		this(new Validacao.Terminal<T>(), "null");
	}

	public ValidacaoRegEx(final String regEx) {
		this(new Validacao.Terminal<T>(), regEx);
	}

	public ValidacaoRegEx(final Validacao<T> restricao, final String regEx) {
		this.validacao = restricao;
		this.regEx = regEx;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null || !objeto.toString().matches(regEx)) {
			throw new IllegalArgumentException(
					"não casa com a expressão regular");
		}

		validacao.valida(objeto);
	}
}
