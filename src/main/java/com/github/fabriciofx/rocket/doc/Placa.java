package com.github.fabriciofx.rocket.doc;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Placa implements Documento {
	private final String numero;

	public Placa(final String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		final String num = new Pattern<String>(
			new NotEmpty<>(
				new NotNull<>()
			), "^[a-zA-Z]{3}\\d{4}$"
		).valid(numero).toUpperCase();
		return String.format(
			"%s-%s",
			num.substring(0, 3),
			num.substring(3, num.length())
		);
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media.with("placa", toString());
	}
}
