package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;

public final class Placa {
	private final String numero;

	public Placa(final String numero) {
		this.numero = new Pattern<String>(
			new NotEmpty<>(
				new NotNull<>()
			), "^[a-zA-Z]{3}\\d{4}$"
		).valid(numero).toUpperCase();
	}

	public String numero() {
		return numero;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", numero.substring(0, 3),
				numero.substring(3, numero.length()));
	}
}
