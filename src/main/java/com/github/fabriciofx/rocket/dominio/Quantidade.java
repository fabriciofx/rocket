package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class Quantidade implements Elemento {
	private final transient double valor;
	private final transient Unidade unidade;

	public Quantidade(final double valor, final Unidade unidade) {
		this.valor = valor;
		this.unidade = new RestNaoNulo<Unidade>(unidade).objeto();
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
