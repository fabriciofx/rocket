package com.github.fabriciofx.rocket.restricao;

public final class RestNaoNulo<T> extends Restricao<T> {
	public RestNaoNulo(final T objeto) {
		super(valida(objeto));
	}

	private static <T> T valida(final T objeto) {
		if (objeto == null) {
			throw new IllegalArgumentException("nulo");
		}

		return objeto;
	}
}
