package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.XmlMedia;

public final class XmlEndereco {
	private final transient Endereco origem;

	public XmlEndereco(final Endereco origem) {
		this.origem = origem;
	}

	@Override
	public String toString() {
		try {
			return origem.print(new XmlMedia("endereco")).toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
