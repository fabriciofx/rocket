package com.github.fabriciofx.rocket.dominio.repositorio;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class UuId
		implements Identificador, Serializable, Comparable<UuId> {
	private static final long serialVersionUID = -6517363452810841857L;

	private final UUID uuid;

	public UuId() {
		this(UUID.randomUUID());
	}

	public UuId(final String uuid) {
		this(UUID.fromString(uuid));
	}

	public UuId(final UUID uuid) {
		this.uuid = Objects.requireNonNull(uuid,
				"argumento 'uuid' não pode ser NULL");
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
