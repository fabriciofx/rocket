package com.github.fabriciofx.rocket.dominio;

public final class IntervaloPadrao<T extends Comparable<T>>
		implements Intervalo<T> {
	private final transient Limite<T> min;
	private final transient Limite<T> max;

	public IntervaloPadrao(final Limite<T> min, final Limite<T> max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean contem(T valor) {
		return !min.maior(valor) && !max.menor(valor);
	}
}
