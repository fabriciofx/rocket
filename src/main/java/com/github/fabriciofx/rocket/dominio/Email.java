package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class Email {
	private final String endereco;

	public Email(final String endereco) {
		valida(endereco);
		this.endereco = endereco;
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Email
				&& endereco.equals(Email.class.cast(o).endereco);
	}

	@Override
	public int hashCode() {
		return 31 + ((endereco == null) ? 0 : endereco.hashCode());
	}

	@Override
	public String toString() {
		return endereco;
	}

	private void valida(final String endereco) {
		Objects.requireNonNull(endereco,
				"argumento 'endereco' não pode ser NULL");

		if (!endereco.matches(
				"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$")) {
			throw new IllegalArgumentException(
					"argumento 'endereco' de e-mail contém caracteres inválidos");
		}
	}
}
