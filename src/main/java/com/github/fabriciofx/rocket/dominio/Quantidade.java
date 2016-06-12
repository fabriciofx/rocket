package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.constraint.NotNull;

public final class Quantidade {
	private final transient double valor;
	private final transient Unidade unidade;

	public Quantidade(final double valor, final Unidade unidade) {
		this.valor = valor;
		this.unidade = new NotNull<Unidade>().valid(unidade);
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
