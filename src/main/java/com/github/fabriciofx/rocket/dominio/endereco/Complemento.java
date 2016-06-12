package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Complemento implements Printer {
	private final String texto;

	public Complemento(final String texto) {
		this.texto = new NotEmpty<String>(new NotNull<>())
				.valid(texto);
	}

	@Override
	public String toString() {
		return texto;
	}

	@Override
	public Media print(Media media) {
		return media.with("complemento", texto);
	}
}
