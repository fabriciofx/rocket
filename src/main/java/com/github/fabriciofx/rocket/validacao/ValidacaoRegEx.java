package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoRegEx<T> implements Restricao<T> {
	private final Restricao<T> restricao;
	private final String regEx;

	public ValidacaoRegEx() {
		this(new Restricao.Terminal<T>(), "null");
	}

	public ValidacaoRegEx(final String regEx) {
		this(new Restricao.Terminal<T>(), regEx);
	}

	public ValidacaoRegEx(final Restricao<T> restricao, final String regEx) {
		this.restricao = restricao;
		this.regEx = regEx;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null || !objeto.toString().matches(regEx)) {
			throw new IllegalArgumentException(
					"não casa com a expressão regular");
		}

		restricao.valida(objeto);
	}
}
