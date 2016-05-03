package com.github.fabriciofx.rocket.restricao;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;

public final class RestTamanho<T> implements Restricao<T> {
	private final transient RestNaoVazia<T> origem;
	private final transient Intervalo.Padrao<Integer> tamanho;

	public RestTamanho(final RestNaoVazia<T> origem,
			final Intervalo.Padrao<Integer> tamanho) {
		this.origem = origem;
		this.tamanho = new RestNaoNulo<Intervalo.Padrao<Integer>>()
				.valido(tamanho);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T valido(final T objeto) {
		origem.valido(objeto);
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
