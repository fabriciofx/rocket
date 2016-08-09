package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public final class ConstFone implements Fone {
	private final transient String numero;
	private final transient Tipo tipo;
	private final transient Operadora operadora;

	public ConstFone(final String numero, final Tipo tipo,
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
	public String numero() throws IOException {
		return numero;
	}

	@Override
	public Tipo tipo() throws IOException {
		return tipo;
	}

	@Override
	public Operadora operadora() throws IOException {
		return operadora;
	}
}
