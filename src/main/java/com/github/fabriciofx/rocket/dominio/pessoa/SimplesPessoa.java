package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.doc.Documento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.NumId;
import com.github.fabriciofx.rocket.media.Media;

public final class SimplesPessoa implements Pessoa {
	private final List<Documento> documentos; 
	
	public SimplesPessoa(final Documento... documentos) {
		this(Arrays.asList(documentos));
	}

	public SimplesPessoa(final List<Documento> documentos) {
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

	@Override
	public Id id() {
		return new NumId(1L);
	}

	@Override
	public void save(final DataSource ds) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Pessoa find(DataSource ds, Id id) throws IOException {
		throw new UnsupportedOperationException();
	}
}
