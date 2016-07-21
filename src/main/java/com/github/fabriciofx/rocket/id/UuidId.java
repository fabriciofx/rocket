package com.github.fabriciofx.rocket.id;

import java.util.UUID;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class UuidId implements Id, Comparable<UuidId> {
	private final transient UUID uuid;

	public UuidId() {
		this(UUID.randomUUID());
	}

	public UuidId(final String uuid) {
		this(UUID.fromString(uuid));
	}

	public UuidId(final UUID uuid) {
		this.uuid = new NotEmpty<UUID>(new NotNull<>()).valid(uuid);
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
	public int compareTo(final UuidId uuid) {
		return this.uuid.compareTo(uuid.uuid);
	}
}
