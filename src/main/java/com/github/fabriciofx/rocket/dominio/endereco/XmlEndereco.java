package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class XmlEndereco {
	private final transient Endereco origem;
	
	public XmlEndereco(final Endereco origem) {
		this.origem = origem;
	}
	
	public Media print() throws IOException {
		return origem.print(new XmlMedia("endereco"));
	}
	
	@Override
	public String toString() {
		try {
			return new XmlFormat(print().toString()).toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}