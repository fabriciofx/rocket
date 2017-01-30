package com.github.fabriciofx.rocket.id;

import java.util.UUID;

public final class IdUu implements Id, Comparable<IdUu> {
	private final UUID uuid;

	public IdUu() {
		this(UUID.randomUUID());
	}

	public IdUu(final String uuid) {
		this(UUID.fromString(uuid));
	}

	public IdUu(final UUID uuid) {
		this.uuid = uuid;
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
	public int compareTo(final IdUu uuid) {
		return this.uuid.compareTo(uuid.uuid);
	}
}
