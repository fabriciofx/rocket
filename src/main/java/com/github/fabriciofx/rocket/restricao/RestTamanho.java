package com.github.fabriciofx.rocket.restricao;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;

public final class RestTamanho<T> extends Restricao<T> {
	public RestTamanho(final RestNaoVazia<T> restricao,
			final Intervalo.Padrao<Integer> tamanho) {
		super(valida(restricao.objeto(), tamanho));
	}

	@SuppressWarnings("unchecked")
	private static <T> T valida(final T objeto,
			final Intervalo.Padrao<Integer> tamanho) {
		if (objeto instanceof Collection
				&& !tamanho.contem(Collection.class.cast(objeto).size())) {
			throw new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof CharSequence && !tamanho.contem(
				CharSequence.class.cast(objeto).toString().trim().length())) {
			throw new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof Map
				&& !tamanho.contem(Map.class.cast(objeto).size())) {
			throw new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof Enumeration && !tamanho.contem(
				Collections.list(Enumeration.class.cast(objeto)).size())) {
			throw new IllegalArgumentException("tamanho fora do intervalo");
		}

		return objeto;
	}
}