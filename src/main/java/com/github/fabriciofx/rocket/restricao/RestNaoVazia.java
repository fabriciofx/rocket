package com.github.fabriciofx.rocket.restricao;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public final class RestNaoVazia<T> implements Restricao<T> {
	private final transient RestNaoNulo<T> origem;
	
	public RestNaoVazia(final RestNaoNulo<T> origem) {
		this.origem = origem;
	}

	@Override
	public T valido(final T objeto) {
		origem.valido(objeto);
		if (objeto instanceof CharSequence && CharSequence.class.cast(objeto)
				.toString().trim().length() == 0) {
			throw new IllegalArgumentException("vazio");
		} else if (objeto instanceof Object[]
				&& Object[].class.cast(objeto).length == 0) {
			throw new IllegalArgumentException("vazio");
		} else if (objeto instanceof Iterator
				&& !Iterator.class.cast(objeto).hasNext()) {
			throw new IllegalArgumentException("vazio");
		} else if (objeto instanceof Collection
				&& Collection.class.cast(objeto).isEmpty()) {
			throw new IllegalArgumentException("vazio");
		} else if (objeto instanceof Map && Map.class.cast(objeto).isEmpty()) {
			throw new IllegalArgumentException("vazio");
		} else if (objeto instanceof Enumeration
				&& !Enumeration.class.cast(objeto).hasMoreElements()) {
			throw new IllegalArgumentException("vazio");
		}
		return objeto;
	}
}
