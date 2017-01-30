package com.github.fabriciofx.rocket.id;

public final class IdHash implements Id, Comparable<IdHash> {
	private final String hash;

	public IdHash(final String hash) {
		this.hash = hash;
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
	public int compareTo(final IdHash IdHash) {
		return (int) (toLong() - IdHash.toLong());
	}
}
