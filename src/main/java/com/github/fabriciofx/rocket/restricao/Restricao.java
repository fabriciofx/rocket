package com.github.fabriciofx.rocket.restricao;

public abstract class Restricao<T> {
	private final T objeto;

	public Restricao(final T objeto) {
		this.objeto = objeto;
	}

	public T objeto() {
		return objeto;
	}
}
