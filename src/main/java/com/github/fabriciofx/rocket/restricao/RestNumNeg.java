package com.github.fabriciofx.rocket.restricao;

public final class RestNumNeg<T extends Number> implements Restricao<T> {
	private final transient RestNaoNulo<T> origem;
	
	public RestNumNeg(final RestNaoNulo<T> origem) {
		this.origem = origem;
	}

	@Override
	public T valido(final T objeto) {
		origem.valido(objeto);
		if (Number.class.cast(objeto).doubleValue() >= 0) {
			throw new IllegalArgumentException("número positivo");
		}
		return objeto;
	}
}
