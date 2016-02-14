package com.github.fabriciofx.rocket.dominio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificador;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class Pessoa {
	private final Identificador id;
	private final Nome nome;
	private final Endereco endereco;
	private final Set<Fone> fones;

	public Pessoa(final Identificador id, final Nome nome,
			final Endereco endereco, final Fone... fones) {
		this(id, nome, endereco, new HashSet<>(Arrays.asList(fones)));
	}

	public Pessoa(final Identificador id, final Nome nome,
			final Endereco endereco, final Set<Fone> fones) {
		this.id = new RestNaoNulo<Identificador>(id).objeto();
		this.nome = new RestNaoNulo<Nome>(nome).objeto();
		this.endereco = new RestNaoNulo<Endereco>(endereco).objeto();
		this.fones = new RestNaoVazia<Set<Fone>>(new RestNaoNulo<>(fones))
				.objeto();
	}

	public Identificador id() {
		return id;
	}

	public Nome nome() {
		return nome;
	}

	public Endereco endereco() {
		return endereco;
	}

	public Set<Fone> fones() {
		return fones;
	}

	@Override
	public String toString() {
		return String.format("Pessoa {id=%s, nome=%s, endereco=%s, fones=%s}",
				id, nome, endereco, Arrays.toString(fones.toArray()));
	}
}
