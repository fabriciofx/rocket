package com.github.fabriciofx.rocket.validacao;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

public final class ValidacaoNaoVazia<T> implements Restricao<T> {
	private final Restricao<T> restricao;

	public ValidacaoNaoVazia() {
		this(new Restricao.Terminal<T>());
	}

	public ValidacaoNaoVazia(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null) {
			new IllegalArgumentException("vazio");
		} else if (objeto instanceof Collection
				&& Collection.class.cast(objeto).isEmpty()) {
			new IllegalArgumentException("vazio");
		} else if (objeto instanceof CharSequence && CharSequence.class
				.cast(objeto).toString().trim().length() == 0) {
			new IllegalArgumentException("vazio");
		} else if (objeto instanceof Map && Map.class.cast(objeto).isEmpty()) {
			new IllegalArgumentException("vazio");
		} else if (objeto instanceof Enumeration
				&& !Enumeration.class.cast(objeto).hasMoreElements()) {
			new IllegalArgumentException("vazio");
		}

		restricao.valida(objeto);
	}
}
