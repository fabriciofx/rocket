package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

public final class Complemento implements Documento {
	private final String texto;

	public Complemento(final String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(texto);
	}
	
	@Override
	public Media print(final Media media) {
		return media.with("complemento", toString());
	}	
}
