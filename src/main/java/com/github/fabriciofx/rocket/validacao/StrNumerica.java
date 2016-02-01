package com.github.fabriciofx.rocket.validacao;

public final class StrNumerica<T extends CharSequence>
		implements Restricao<T> {
	private final Restricao<T> restricao;

	public StrNumerica() {
		this(new Restricao.Terminal<T>());
	}

	public StrNumerica(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (!objeto.toString().matches("[0-9]+")) {
			throw new IllegalArgumentException("apenas numeros");
		}

		restricao.valida(objeto);
	}
}
