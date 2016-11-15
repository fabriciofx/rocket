package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

public final class Complemento implements Documento {
	private final String texto;

	public Complemento(final String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return texto;
	}
	
	@Override
	public Media print(final Media media) {
		return media.with("complemento", texto);
	}	
}
