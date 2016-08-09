package com.github.fabriciofx.rocket.dominio.pessoa.docs;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.XmlMedia;

public final class XmlDocumentos {
	private final transient Documentos origem;
	
	public XmlDocumentos(final Documentos origem) {
		this.origem = origem;
	}
	
	@Override
	public String toString() {
		try {
			return origem.print(new XmlMedia("documentos")).toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
