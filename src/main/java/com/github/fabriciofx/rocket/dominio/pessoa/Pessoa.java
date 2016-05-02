package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Pessoa {
	<T> T elemento(Class<T> tipo) throws IOException;

	Media imprime(Media media);

	public final class Simples implements Pessoa, Identificavel<Id> {
		private final transient Id id;
		private final transient Array<Elemento> elementos;

		public Simples(final Id id, final Elemento... elementos) {
			this(id, Arrays.asList(elementos));
		}

		public Simples(final Id id, final List<Elemento> elementos) {
			this.id = id;
			this.elementos = new Array<>(elementos);
		}

		@Override
		public Id id() {
			return id;
		}

		@Override
		public <T> T elemento(final Class<T> tipo) throws IOException {
			for (final Elemento e : elementos) {
				if (e.getClass().equals(tipo)) {
					return tipo.cast(e);
				}
			}
			throw new IOException(
					String.format("elemento do tipo %s não encontrado",
							tipo.getSimpleName()));
		}

		@Override
		public Media imprime(Media media) {
			media = media.with("id", id.toString());
			for (final Elemento e : elementos) {
				media.with(e.getClass().getSimpleName(), e.toString());
			}
			return media;
		}
	}
}
