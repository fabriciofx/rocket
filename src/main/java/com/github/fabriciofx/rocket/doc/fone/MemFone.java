package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public final class MemFone implements Fone {
	private final String numero;
	private final Tipo tipo;
	private final Operadora operadora;

	public MemFone(final String numero, final Tipo tipo,
			final Operadora operadora) {
		this.numero = numero;
		this.tipo = tipo;
		this.operadora = operadora;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media
				.with("numero", numero)
				.with("tipo", tipo)
				.with("operadora", operadora);
	}

	@Override
	public void apague() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void atualize(String numero, Tipo tipo, Operadora operadora)
		throws IOException {
		throw new UnsupportedOperationException();
	}
}