package com.github.fabriciofx.rocket.restricao;

public final class RestNaoNulo<T> implements Restricao<T> {
	@Override
	public T valido(final T objeto) {
		if (objeto == null) {
			throw new IllegalArgumentException("nulo");
		}
		return objeto;
	}
}
