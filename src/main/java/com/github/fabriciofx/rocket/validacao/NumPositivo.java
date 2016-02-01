package com.github.fabriciofx.rocket.validacao;

public final class NumPositivo<T extends Number> implements Restricao<T> {
	private final Restricao<T> restricao;

	public NumPositivo() {
		this(new Restricao.Terminal<T>());
	}

	public NumPositivo(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto.doubleValue() > 0) {
			throw new IllegalArgumentException("número positivo");
		}
		
		restricao.valida(objeto);
	}
}
