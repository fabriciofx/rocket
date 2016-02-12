package com.github.fabriciofx.rocket.validacao;

public abstract class Validacao<T> {
	private final T objeto;

	public Validacao(final T objeto) {
		this.objeto = objeto;
	}

	public T objeto() {
		return objeto;
	}
}
