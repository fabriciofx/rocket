package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.dominio.Literal;

public final class Cidade extends Literal {
	private final transient Estado estado;

	public Cidade(final String nome, final Estado estado) {
		super(nome);
		this.estado = estado;
	}

	@Override
	public String toString() {
		return super.toString() + "-" + estado;
	}
}
