package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNumPos<T extends Number> implements Restricao<T> {
	private final Restricao<T> restricao;

	public ValidacaoNumPos() {
		this(new Restricao.Terminal<T>());
	}

	public ValidacaoNumPos(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto.doubleValue() < 0) {
			throw new IllegalArgumentException("número negativo");
		}

		restricao.valida(objeto);
	}
}
