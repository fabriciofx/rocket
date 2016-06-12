package com.github.fabriciofx.rocket.range;

public final class Limit<T extends Comparable<T>> {
	private final T value;

	public Limit(final T value) {
		this.value = value;
	}

	public boolean equals(final T value) {
		return this.value.compareTo(value) == 0;
	}

	public boolean greater(final T value) {
		return this.value.compareTo(value) > 0;
	}

	public boolean less(final T valor) {
		return this.value.compareTo(valor) < 0;
	}
}
