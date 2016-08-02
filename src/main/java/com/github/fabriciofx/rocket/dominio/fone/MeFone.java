package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public final class MeFone implements Fone {
	private final transient String numero;

	public MeFone(final String numero) {
		this.numero = numero;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media.with("fone", numero);
	}

	@Override
	public String numero() throws IOException {
		return numero;
	}

	@Override
	public void salva() throws IOException {
	}

	@Override
	public void apaga() throws IOException {
	}
}
