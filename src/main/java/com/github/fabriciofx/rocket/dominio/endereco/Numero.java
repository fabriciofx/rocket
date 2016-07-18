package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Numero implements Printer {
	private final String numero;

	public Numero(final String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return numero;
	}

	@Override
	public Media print(final Media media) {
		return media.with("numero", numero);
	}
}
