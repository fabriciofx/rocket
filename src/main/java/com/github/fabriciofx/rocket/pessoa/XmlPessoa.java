package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.XmlMedia;

public final class XmlPessoa {
	private final Pessoa origem;

	public XmlPessoa(final Pessoa origem) {
		this.origem = origem;
	}

	@Override
	public String toString() {
		try {
			return origem.about(new XmlMedia("pessoa")).toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
