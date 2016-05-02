package com.github.fabriciofx.rocket.dominio.endereco;

import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.pessoa.Atributo;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Endereco {
	Selo selo(Class<?> tipo);

	Media imprime(Media media);

	public final class Simples implements Endereco, Atributo {
		private final transient Array<Selo> selos;

		public Simples(final Selo... selos) {
			this(Arrays.asList(selos));
		}

		public Simples(final List<Selo> selos) {
			this.selos = new Array<>(selos);
		}

		@Override
		public Selo selo(final Class<?> tipo) {
			for (final Selo s : selos) {
				if (s.getClass().equals(tipo)) {
					return s;
				}
			}
			throw new IllegalArgumentException("selo inexistente");
		}

		@Override
		public Media imprime(final Media midia) {
			for (final Selo s : selos) {
				midia.with(s.getClass().getSimpleName(), s.toString());
			}
			return midia;
		}
	}

	public final class Entidade
			implements Identificavel<Id>, Endereco, Atributo {
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
		public Selo selo(final Class<?> tipo) {
			return origem.selo(tipo);
		}

		@Override
		public Media imprime(final Media midia) {
			return origem.imprime(midia).with("Id: ", id.toString());
		}
	}
}
