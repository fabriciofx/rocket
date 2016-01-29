package com.github.fabriciofx.rocket.dominio.intervalo;

public final class Intervalo<T extends Comparable<T>> {
	private final Limite<T> min;
	private final Limite<T> max;

	public Intervalo(final Limite<T> min, final Limite<T> max) {
		this.min = min;
		this.max = max;
	}

	public boolean contem(final T valor) {
		return !min.maior(valor) && !max.menor(valor);
	}
}
