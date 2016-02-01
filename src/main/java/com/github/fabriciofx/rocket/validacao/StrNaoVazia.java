package com.github.fabriciofx.rocket.validacao;

public final class StrNaoVazia<T extends CharSequence> implements Restricao<T> {
	private final Restricao<T> restricao;

	public StrNaoVazia() {
		this(new Restricao.Terminal<T>());
	}

	public StrNaoVazia(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto.length() <= 0) {
			throw new IllegalArgumentException("vazia");
		}

		restricao.valida(objeto);
	}
}
