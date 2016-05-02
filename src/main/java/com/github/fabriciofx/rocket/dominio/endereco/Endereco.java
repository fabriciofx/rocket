package com.github.fabriciofx.rocket.dominio.endereco;

import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Endereco {
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
		public Media imprime(final Media midia) {
			for (final Elemento s : elementos) {
				midia.with(s.getClass().getSimpleName(), s.toString());
			}
			return midia;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			for (final Elemento s : elementos) {
				sb.append(s.toString()).append(";");
			}
			return sb.toString();
		}
	}

	public final class Entidade
			implements Identificavel<Id>, Endereco, Elemento {
		private final transient Id id;
		private final transient Endereco.Simples origem;

		public Entidade(final Id id, final Endereco.Simples origem) {
			this.id = id;
			this.origem = origem;
		}

		@Override
		public Id id() {
			return id;
		}

		@Override
		public Media imprime(final Media midia) {
			return origem.imprime(midia).with("Id: ", id.toString());
		}
	}
}
