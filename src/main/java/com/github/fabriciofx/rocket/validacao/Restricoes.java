package com.github.fabriciofx.rocket.validacao;

import java.util.Arrays;
import java.util.List;

public final class Restricoes<T> implements Restricao<T> {
	private final List<Restricao<T>> restricoes;

	@SafeVarargs
	public Restricoes(final Restricao<T>... restricoes) {
		this(Arrays.asList(restricoes));
	}

	public Restricoes(final List<Restricao<T>> restricoes) {
		this.restricoes = restricoes;
	}

	@Override
	public void valida(T objeto) throws Exception {
		for (final Restricao<T> restricao : restricoes) {
			restricao.valida(objeto);
		}
	}
}
