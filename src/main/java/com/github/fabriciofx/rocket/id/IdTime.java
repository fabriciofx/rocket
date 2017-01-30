package com.github.fabriciofx.rocket.id;

public final class IdTime implements Id, Comparable<IdTime> {
	private final long milliseconds;

	public IdTime(final long milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int sequential(final long n) {
		return (int) (milliseconds - ((milliseconds / 100L) * 100L));
	}

	// Year.Month.Day.Hour.Minute.Second.Nanosecond.(RANDOM between 0 and 99)
	// Exemplo: 2015.12.28.22.52.13.123.45
	@Override
	public long toLong() {
		return milliseconds;
	}

	@Override
	public String toString() {
		return Long.toString(milliseconds);
	}

	@Override
	public int compareTo(final IdTime timeId) {
		return (int) (milliseconds - timeId.milliseconds);
	}

	@Override
	public int hashCode() {
		return (int) (31 * milliseconds);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null &&
			o instanceof IdTime &&
			milliseconds == IdTime.class.cast(o).milliseconds;
	}
}
