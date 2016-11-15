package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

public final class Numero implements Documento {
	private final String numero;

	public Numero(final String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(numero);
	}

	@Override
	public Media print(final Media media) {
		return media.with("numero", toString());
	}
}
