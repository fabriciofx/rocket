package com.github.fabriciofx.rocket.dominio;

public final class Placa {
	private final String numero;

	public Placa(final String numero) {
		if (numero == null || !numero.matches("^[a-zA-Z]{3}\\d{4}$")) {
			throw new IllegalArgumentException("número de placa inválido");
		}

		this.numero = numero.toUpperCase();
	}

	public String numero() {
		return numero;
	}

	@Override
	public String toString() {
		return numero.substring(0, 3) + "-"
				+ numero.substring(3, numero.length());
	}
}
