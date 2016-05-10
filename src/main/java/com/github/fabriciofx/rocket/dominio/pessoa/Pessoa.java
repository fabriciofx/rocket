package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Pessoa {
	String elemento(String nome) throws IOException;

	Media imprime(Media media);

	public final class Simples implements Pessoa, Identificavel<Id> {
		private final transient Id id;
		private final transient Array<Object> elementos;

		public Simples(final Id id, final Object... elementos) {
			this(id, Arrays.asList(elementos));
		}

		public Simples(final Id id, final List<Object> elementos) {
			this.id = id;
			this.elementos = new Array<>(elementos);
		}

		@Override
		public Id id() {
			return id;
		}

		@Override
		public String elemento(final String nome) throws IOException {
			for (final Object o : elementos) {
				if (o.getClass().getSimpleName().toLowerCase().equals(nome)) {
					return o.toString();
				}
			}
			throw new IOException(
					String.format("elemento do tipo %s não encontrado", nome));
		}		

		@Override
		public Media imprime(final Media media) {
			Media m = media.with("id", id.toString());
			for (final Object o : elementos) {
				m = m.with(o.getClass().getSimpleName(), o.toString());
			}
			return m;
		}
	}
}
