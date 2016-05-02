package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Endereco {
	<T> T elemento(Class<T> tipo) throws IOException;

	Media imprime(Media media);

	public final class Simples implements Endereco, Elemento {
		private final transient Array<Elemento> elementos;

		public Simples(final Elemento... elementos) {
			this(Arrays.asList(elementos));
		}

		public Simples(final List<Elemento> elementos) {
			this.elementos = new Array<>(elementos);
		}

		@Override
		public Media imprime(final Media media) {
			for (final Elemento e : elementos) {
				media.with(e.getClass().getSimpleName(), e.toString());
			}
			return media;
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
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			for (final Elemento e : elementos) {
				sb.append(e.toString()).append(";");
			}
			return sb.toString();
		}
	}

	public final class Entidade
			implements Identificavel<Id>, Endereco, Elemento {
		private final transient Id id;
		private final transient Endereco.Simples origem;

		public Entidade(final Endereco.Simples origem, final Id id) {
			this.origem = origem;
			this.id = id;
		}

		@Override
		public Id id() {
			return id;
		}

		@Override
		public <T> T elemento(final Class<T> tipo) throws IOException {
			return origem.elemento(tipo);
		}

		@Override
		public Media imprime(Media media) {
			media = media.with("id", id.toString());
			return origem.imprime(media);
		}
	}
}
