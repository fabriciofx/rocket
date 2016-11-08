package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Fone;

public final class XmlFones {
	private final transient Fones origem;
	
	public XmlFones(final Fones origem) {
		this.origem = origem;
	}
	
	@Override
	public String toString() {
		try {
			final StringBuilder sb = new StringBuilder();
			sb.append("<fones>");
			for (final Fone f : origem.todos()) {
				sb.append(f.toString());
			}
			sb.append("</fones>");
			return sb.toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
