package com.github.fabriciofx.rocket.dominio.repositorio;

import java.io.Serializable;
import java.util.Objects;

public final class HashId
		implements Identificador, Serializable, Comparable<HashId> {
	private static final long serialVersionUID = 7647835760601201811L;

	private final String hash;

	public HashId(final String hash) {
		this.hash = Objects.requireNonNull(hash,
				"argumento 'hash' não pode ser NULL");
	}

	@Override
	public long toLong() {
		return hash.hashCode();
	}

	@Override
	public String toString() {
		return hash;
	}

	@Override
	public int compareTo(final HashId hashId) {
		return (int) (toLong() - hashId.toLong());
	}
}
