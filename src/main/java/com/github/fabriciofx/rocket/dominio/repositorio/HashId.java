package com.github.fabriciofx.rocket.dominio.repositorio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class HashId implements Id, Comparable<HashId> {
	private final transient String hash;

	public HashId(final String hash) {
		this.hash = new RestNaoVazia<String>(new RestNaoNulo<>()).valido(hash);
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
