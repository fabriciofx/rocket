package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Endereco {
	String elemento(String nome) throws IOException;

	Media imprime(Media media);

	public final class Simples implements Endereco {
		private final transient Array<Object> elementos;

		public Simples(final Object... elementos) {
			this(Arrays.asList(elementos));
		}

		public Simples(final List<Object> elementos) {
			this.elementos = new Array<>(elementos);
		}

		@Override
		public Media imprime(final Media media) {
			Media m = media;
			for (final Object o : elementos) {
				m = m.with(o.getClass().getSimpleName(), o.toString());
			}
			return m;
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
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			for (final Object o : elementos) {
				sb.append(o.toString()).append(";");
			}
			return sb.toString();
		}
	}

	public final class Entidade implements Identificavel<Id>, Endereco {
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
		public String elemento(final String nome) throws IOException {
			return origem.elemento(nome);
		}

		@Override
		public Media imprime(final Media media) {
			return origem.imprime(media.with("id", id.toString()));
		}
	}
}
