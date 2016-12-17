package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.Mod11;
import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Cpf implements Documento {
	private final String numero;

	public Cpf(final String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return new Mod11<String>(
			new NotEmpty<>(
				new NotNull<>()
			)
		).valid(numero);
	}

	@Override
	public Media print(final Media media) {
		return media.with("cpf", toString());
	}
}
