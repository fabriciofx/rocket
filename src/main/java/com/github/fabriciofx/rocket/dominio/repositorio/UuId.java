package com.github.fabriciofx.rocket.dominio.repositorio;

import java.io.Serializable;
import java.util.UUID;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class UuId implements Id, Serializable, Comparable<UuId> {
	private static final long serialVersionUID = 5856999591152433138L;

	private final UUID uuid;

	public UuId() {
		this(UUID.randomUUID());
	}

	public UuId(final String uuid) {
		this(UUID.fromString(uuid));
	}

	public UuId(final UUID uuid) {
		this.uuid = new RestNaoVazia<>(new RestNaoNulo<>(uuid)).objeto();
	}

	@Override
	public long toLong() {
		return uuid.hashCode();
	}

	@Override
	public String toString() {
		return uuid.toString().toUpperCase();
	}

	@Override
	public int compareTo(final UuId uuid) {
		return this.uuid.compareTo(uuid.uuid);
	}
}
