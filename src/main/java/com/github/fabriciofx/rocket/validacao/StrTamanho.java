package com.github.fabriciofx.rocket.validacao;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;

public final class StrTamanho<T extends CharSequence> implements Restricao<T> {
	private final Restricao<T> restricao;
	private final Intervalo.Padrao<Integer> tamanho;

	public StrTamanho() {
		this(new Restricao.Terminal<>(), null);
	}

	public StrTamanho(final Restricao<T> restricao,
			final Intervalo.Padrao<Integer> tamanho) {
		this.restricao = restricao;
		this.tamanho = tamanho;
	}

	@Override
	public void valida(final T objeto) {
		if (!tamanho.contem(objeto.length())) {
			throw new IllegalArgumentException(
					"tamanho da string fora do intervalo");
		}
		restricao.valida(objeto);
	}
}
