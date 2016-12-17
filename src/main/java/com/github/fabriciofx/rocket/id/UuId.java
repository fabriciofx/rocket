package com.github.fabriciofx.rocket.id;

import java.util.UUID;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class UuId implements Id, Comparable<UuId> {
	private final UUID uuid;

	public UuId() {
		this(UUID.randomUUID());
	}

	public UuId(final String uuid) {
		this(UUID.fromString(uuid));
	}

	public UuId(final UUID uuid) {
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
	public int compareTo(final UuId uuid) {
		return this.uuid.compareTo(uuid.uuid);
	}
}
