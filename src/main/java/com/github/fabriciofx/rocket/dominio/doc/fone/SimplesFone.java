package com.github.fabriciofx.rocket.dominio.doc.fone;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.NumId;
import com.github.fabriciofx.rocket.media.Media;

public final class SimplesFone implements Fone {
	private final transient String numero;
	private final transient Tipo tipo;
	private final transient Operadora operadora;

	public SimplesFone(final String numero, final Tipo tipo,
			final Operadora operadora) {
		this.numero = numero;
		this.tipo = tipo;
		this.operadora = operadora;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media.with("numero", numero)
			.with("tipo", tipo.toString())
			.with("operadora", operadora.toString());
	}

	@Override
	public Id id() {
		return new NumId(0L);
	}

	@Override
	public void save(final DataSource ds) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Fone find(final DataSource ds, final Id id) throws IOException {
		throw new UnsupportedOperationException();
	}
}
