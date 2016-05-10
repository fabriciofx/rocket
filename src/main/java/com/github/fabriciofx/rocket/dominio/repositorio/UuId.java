package com.github.fabriciofx.rocket.dominio.repositorio;

import java.util.UUID;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class UuId implements Id, Comparable<UuId> {
	private final transient UUID uuid;

	public UuId() {
		this(UUID.randomUUID());
	}

	public UuId(final String uuid) {
		this(UUID.fromString(uuid));
	}

	public UuId(final UUID uuid) {
		this.uuid = new RestNaoVazia<UUID>(new RestNaoNulo<>()).valido(uuid);
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
