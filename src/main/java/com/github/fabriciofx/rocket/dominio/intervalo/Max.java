package com.github.fabriciofx.rocket.dominio.intervalo;

public final class Max<T extends Comparable<T>> {
	private final T limite;

	public Max(final T limite) {
		this.limite = limite;
	}
	
	public boolean igual(final T valor) {
		return limite.compareTo(valor) == 0;
	}
	
	public boolean ultrapassado(final T valor) {
		return limite.compareTo(valor) < 0;
	}
}
