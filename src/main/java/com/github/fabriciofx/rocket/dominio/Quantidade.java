package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class Quantidade {
	private final double valor;
	private final Unidade unidade;

	public Quantidade(final double valor, final Unidade unidade) {
		this.valor = valor;
		this.unidade = Objects.requireNonNull(unidade,
				"argumento 'unidade' da quantidade não pode ser NULL");
	}

	public double valor() {
		return valor;
	}

	public Unidade unidade() {
		return unidade;
	}

	@Override
	public String toString() {
		return String.format("%g (%s)", valor, unidade);
	}
}
