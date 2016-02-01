package com.github.fabriciofx.rocket.validacao;

public final class NumNegativo<T extends Number> implements Restricao<T> {
	private final Restricao<T> restricao;

	public NumNegativo() {
		this(new Restricao.Terminal<T>());
	}

	public NumNegativo(final Restricao<T> restricao) {
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
