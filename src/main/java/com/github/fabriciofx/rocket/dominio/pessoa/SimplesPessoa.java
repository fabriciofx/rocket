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
	private final transient Id id;
	private final transient List<Documento> documentos; 
	
	public SimplesPessoa(final Documento... documentos) {
		this(new NumId(0L), Arrays.asList(documentos));
	}	
	
	public SimplesPessoa(final Id id, final Documento... documentos) {
		this(id, Arrays.asList(documentos));
	}

	public SimplesPessoa(final Id id, final List<Documento> documentos) {
		this.id = id;
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
		return id;
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
