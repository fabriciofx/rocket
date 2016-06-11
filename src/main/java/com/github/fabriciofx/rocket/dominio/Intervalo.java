package com.github.fabriciofx.rocket.dominio;

public interface Intervalo<T extends Comparable<T>> {
	boolean contem(T valor);
}
