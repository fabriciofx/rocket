package com.github.fabriciofx.rocket.dominio;

public interface Periodo<T extends Comparable<T>> extends Intervalo<T> {
	T inicio();

	T termino();
}
