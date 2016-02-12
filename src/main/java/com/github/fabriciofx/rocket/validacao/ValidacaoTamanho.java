package com.github.fabriciofx.rocket.validacao;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;

public final class ValidacaoTamanho<T> implements Restricao<T> {
	private final Restricao<T> restricao;
	private final Intervalo.Padrao<Integer> tamanho;

	public ValidacaoTamanho() {
		this(new Restricao.Terminal<>(), null);
	}

	public ValidacaoTamanho(final Restricao<T> restricao,
			final Intervalo.Padrao<Integer> tamanho) {
		this.restricao = restricao;
		this.tamanho = tamanho;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null) {
			new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof Collection
				&& !tamanho.contem(Collection.class.cast(objeto).size())) {
			new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof CharSequence && !tamanho.contem(
				CharSequence.class.cast(objeto).toString().trim().length())) {
			new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof Map
				&& !tamanho.contem(Map.class.cast(objeto).size())) {
			new IllegalArgumentException("tamanho fora do intervalo");
		} else if (objeto instanceof Enumeration && !tamanho.contem(
				Collections.list(Enumeration.class.cast(objeto)).size())) {
			new IllegalArgumentException("tamanho fora do intervalo");
		}

		restricao.valida(objeto);
	}
}
