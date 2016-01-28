package com.github.fabriciofx.rocket.dominio.regra;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Regras<T, R> {
	private final Set<Regra<T, R>> regras;

	@SafeVarargs
	public Regras(final Regra<T, R>... regras) {
		this(new HashSet<>(Arrays.asList(regras)));
	}

	public Regras(final Set<Regra<T, R>> regras) {
		this.regras = Objects.requireNonNull(regras,
				"conjunto de regras inválidas (null");
	}

	public R aplica(final T objeto) {
		for (final Regra<T, R> r : regras) {
			if (r.aplicavel(objeto)) {
				return r.aplica(objeto);
			}
		}

		throw new RuntimeException(
				"não foi encontrada nenhuma regra aplicável!");
	}
}
