package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.immutable.Array;

public interface Pessoa {
	Media imprime(Media media);

	public final class Simples implements Pessoa, Identificavel<Id> {
		private final transient Id id;
		private final transient Array<Serializable> atributos;

		public Simples(final Id id, final Serializable... atributos) {
			this(id, Arrays.asList(atributos));
		}

		public Simples(final Id id, final List<Serializable> atributos) {
			this.id = id;
			this.atributos = new Array<>(atributos);
		}

		@Override
		public Id id() {
			return id;
		}

		@Override
		public Media imprime(Media media) {
			media = media.with("id", id.toString());
			for (final Serializable a : atributos) {
				media.with(a.getClass().getSimpleName(), a.toString());
			}
			return media;
		}
	}
}
