package com.github.fabriciofx.rocket.dominio.intervalo;

public final class Intervalo<T extends Comparable<T>> {
	private final Min<T> min;
	private final Max<T> max;

	public Intervalo(final Min<T> min, final Max<T> max) {
		this.min = min;
		this.max = max;
	}

	public boolean contem(final T valor) {
		return !min.ultrapassado(valor) && !max.ultrapassado(valor);
	}
}
