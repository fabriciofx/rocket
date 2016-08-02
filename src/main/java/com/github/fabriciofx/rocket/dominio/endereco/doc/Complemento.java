package com.github.fabriciofx.rocket.dominio.endereco.doc;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Complemento implements Printer {
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
