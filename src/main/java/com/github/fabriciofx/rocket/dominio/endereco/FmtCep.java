package com.github.fabriciofx.rocket.dominio.endereco;

public final class FmtCep {
	private final Cep cep;

	public FmtCep(final Cep cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return cep.toString().substring(0, 5) + "-"
				+ cep.toString().substring(5, 8);
	}
}
