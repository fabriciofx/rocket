package com.github.fabriciofx.rocket.validacao;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public final class ValidacaoNaoVazia<T> extends Validacao<T> {
	public ValidacaoNaoVazia(final ValidacaoNaoNulo<T> validacao) {
		super(valida(validacao.objeto()));
	}

	private static <T> T valida(final T objeto) {
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
