package com.github.fabriciofx.rocket.dominio.endereco;

import java.util.Objects;

public final class Cep {
	private final String numero;

	public Cep(final String numero) {
		Objects.requireNonNull(numero, "número de CEP não pode ser NULL");

		if (!numero.matches("[0-9]{8}")) {
			throw new IllegalArgumentException(
					"número de CEP deve ser composto apenas por números");
		}

		this.numero = numero;
	}

	public String numero() {
		return numero;
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cep
				&& numero.equals(Cep.class.cast(o).numero());
	}

	@Override
	public String toString() {
		return numero;
	}
}
