package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.Mod13;
import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Cnpj implements Documento {
	private final String numero;

	public Cnpj(final String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return new Mod13<String>(
			new NotEmpty<>(
				new NotNull<>()
			)
		).valid(numero);
	}

	@Override
	public Media print(final Media media) {
		return media.with("cnpj", toString());
	}
}
