package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class Nome {
	private final String completo;

	public Nome(final String completo) {
		Objects.requireNonNull(completo,
				"argumento 'completo' do nome não pode ser NULL");

		if (completo.isEmpty()) {
			throw new IllegalArgumentException(
					"argumento 'completo' não pode ser string vazia");
		}

		if (completo.matches(".*?(-?\\d+).*")) {
			throw new IllegalArgumentException(
					"argumento 'completo' do nome possui caracteres inválidos");
		}

		this.completo = completo.trim().replaceAll("\\s+", " ");
	}

	public String completo() {
		return completo;
	}

	public String primeiro() {
		return completo.split("\\s")[0];
	}

	public String ultimo() {
		final String[] parts = completo.split("\\s");
		final int pos = parts.length - 1 > 0 ? parts.length - 1 : 0;

		return parts[pos];
	}

	public String primeiroUltimo() {
		return primeiro() + " " + ultimo();
	}

	public String ultimoPrimeiro() {
		return ultimo() + " " + primeiro();
	}

	public String americano() {
		return ultimo() + ", " + primeiro();
	}

	@Override
	public final boolean equals(final Object o) {
		return o != null && o instanceof Nome
				&& completo.equals(Nome.class.cast(o).completo);
	}

	@Override
	public final int hashCode() {
		return 31 + ((completo == null) ? 0 : completo.hashCode());
	}

	@Override
	public final String toString() {
		return completo;
	}
}
