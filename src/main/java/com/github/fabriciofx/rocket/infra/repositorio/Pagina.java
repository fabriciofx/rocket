package com.github.fabriciofx.rocket.infra.repositorio;

import java.util.List;
import java.util.Objects;

import com.jcabi.immutable.Array;

public final class Pagina<T> {
	private final Array<T> itens;
	private final long numero;

	public Pagina(final List<T> itens, final long numero) {
		this(new Array<T>(itens), numero);
	}

	public Pagina(final Array<T> itens, final long numero) {
		this.itens = Objects.requireNonNull(itens,
				"argumento 'itens' não pode ser NULL");
		this.numero = numero;
	}

	public Array<T> itens() {
		return itens;
	}

	public long numero() {
		return numero;
	}
}
