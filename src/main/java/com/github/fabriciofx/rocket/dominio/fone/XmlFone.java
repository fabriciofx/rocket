package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class XmlFone {
	private final transient Fone origem;
	
	public XmlFone(final Fone origem) {
		this.origem = origem;
	}
	
	@Override
	public String toString() {
		try {
			return new XmlFormat(
				origem.print(
					new XmlMedia("fone")
				).toString()
			).toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
