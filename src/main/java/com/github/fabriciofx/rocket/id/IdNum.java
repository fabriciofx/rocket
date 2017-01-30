package com.github.fabriciofx.rocket.id;

public final class IdNum implements Id, Comparable<IdNum> {
	private final long num;

	public IdNum(final long num) {
		this.num = num;
	}

	@Override
	public int compareTo(final IdNum numId) {
		return (int) (num - numId.num);
	}

	@Override
	public int hashCode() {
		return (int) (31 * num);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null &&
			o instanceof IdNum &&
			num == IdNum.class.cast(o).num;
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
