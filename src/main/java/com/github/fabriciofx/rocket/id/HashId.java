package com.github.fabriciofx.rocket.id;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class HashId implements Id, Comparable<HashId> {
	private final String hash;

	public HashId(final String hash) {
		this.hash = new NotEmpty<String>(new NotNull<>()).valid(hash);
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
