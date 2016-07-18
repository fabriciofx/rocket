package com.github.fabriciofx.rocket.dominio.doc.endereco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Endereco implements Documento {
	private final List<Printer> printers;

	public Endereco(final Printer... printers) {
		this(Arrays.asList(printers));
	}
	
	public Endereco(final List<Printer> printers) {
		this.printers = new ArrayList<>(printers);
	}

	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final Printer p : printers) {
			m = p.print(m);
		}
		return m;
	}
}
