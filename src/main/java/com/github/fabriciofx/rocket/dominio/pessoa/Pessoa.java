package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Pessoa {
	String documento(String nome) throws IOException;

	Media imprime(Media media);

	public final class Simples implements Pessoa, Identificavel<Id> {
		private final transient Id id;
		private final transient Array<Object> documentos;

		public Simples(final Id id, final Object... documentos) {
			this(id, Arrays.asList(documentos));
		}

		public Simples(final Id id, final List<Object> documentos) {
			this.id = id;
			this.documentos = new Array<>(documentos);
		}

		@Override
		public Id id() {
			return id;
		}

		@Override
		public String documento(final String nome) throws IOException {
			for (final Object d : documentos) {
				if (d.getClass().getSimpleName().toLowerCase().equals(nome)) {
					return d.toString();
				}
			}
			throw new IOException(
					String.format("documento %s não encontrado", nome));
		}		

		@Override
		public Media imprime(final Media media) {
			Media m = media.with("id", id.toString());
			for (final Object d : documentos) {
				m = m.with(d.getClass().getSimpleName(), d.toString());
			}
			return m;
		}
	}
}
