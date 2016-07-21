package com.github.fabriciofx.rocket.id;

public final class NumId implements Id, Comparable<NumId> {
	private final transient long num;

	public NumId(final long num) {
		this.num = num;
	}

	@Override
	public int compareTo(final NumId numId) {
		return (int) (num - numId.num);
	}

	@Override
	public int hashCode() {
		return (int) (31 * num);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof NumId
				&& num == NumId.class.cast(o).num;
	}

	@Override
	public long toLong() {
		return num;
	}
	
	@Override
	public String toString() {
		return Long.toString(num);
	}
}
