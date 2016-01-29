package com.github.fabriciofx.rocket.dominio.intervalo;

public final class Limite<T extends Comparable<T>> {
	private final T valor;

	public Limite(final T valor) {
		this.valor = valor;
	}

	public boolean igual(final T valor) {
		return this.valor.compareTo(valor) == 0;
	}

	public boolean maior(final T valor) {
		return this.valor.compareTo(valor) > 0;
	}

	public boolean menor(final T valor) {
		return this.valor.compareTo(valor) < 0;
	}
}
