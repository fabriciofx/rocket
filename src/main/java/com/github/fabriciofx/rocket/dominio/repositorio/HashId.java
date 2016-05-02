package com.github.fabriciofx.rocket.dominio.repositorio;

import java.io.Serializable;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class HashId implements Id, Serializable, Comparable<HashId> {
	private static final long serialVersionUID = 626612574061974733L;

	private final transient String hash;

	public HashId(final String hash) {
		this.hash = new RestNaoVazia<>(new RestNaoNulo<>(hash)).objeto();
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
