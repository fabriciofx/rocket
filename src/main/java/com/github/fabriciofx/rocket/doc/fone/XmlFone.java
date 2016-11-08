package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.XmlMedia;

public final class XmlFone {
	private final Fone origem;

	public XmlFone(final Fone origem) {
		this.origem = origem;
	}

	@Override
	public String toString() {
		try {
			return origem.print(new XmlMedia("fone")).toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
