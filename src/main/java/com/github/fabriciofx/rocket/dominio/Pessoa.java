package com.github.fabriciofx.rocket.dominio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificador;

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
		this.id = Objects.requireNonNull(id,
				"identificador de pessoa não pode ser NULL");
		this.nome = Objects.requireNonNull(nome,
				"nome de pessoa não pode ser NULL");
		this.endereco = Objects.requireNonNull(endereco,
				"endereço de pessoa não pode ser NULL");
		this.fones = Objects.requireNonNull(fones,
				"telefone(s) de pessoa não pode ser NULL");
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
