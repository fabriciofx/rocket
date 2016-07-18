package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.documento.Documento;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Pessoa implements Printer {
	private final List<Documento> documentos; 
	
	public Pessoa(final Documento... documentos) {
		this(Arrays.asList(documentos));
	}

	public Pessoa(final List<Documento> documentos) {
		this.documentos = new ArrayList<>(documentos);
	}
	
	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final Documento d : documentos) {
			m = d.print(m);
		}
		return m;
	}
}
