package com.github.fabriciofx.rocket.id;

import java.time.LocalDateTime;

import com.github.fabriciofx.rocket.misc.Rand;

public final class TimeId implements Id, Comparable<TimeId> {
	private final transient long milliseconds;

	public TimeId() {
		this(LocalDateTime.now());
	}

	public TimeId(final LocalDateTime dateTime) {
		this(dateTime, new Rand().num(10, 99));
	}

	public TimeId(final LocalDateTime dateTime, final int sequential) {
		this(localDateTimeToLong(dateTime) + sequential);
	}

	public TimeId(final long milliseconds) {
		this.milliseconds = milliseconds;
	}

	public LocalDateTime dateTime() {
		final int year = (int) (milliseconds / 1000000000000000L);
		final int month = (int) ((milliseconds / 10000000000000L)
				- ((milliseconds / 1000000000000000L) * 100L));
		final int day = (int) ((milliseconds / 100000000000L)
				- ((milliseconds / 10000000000000L) * 100L));
		final int hour = (int) ((milliseconds / 1000000000L)
				- ((milliseconds / 100000000000L) * 100L));
		final int minute = (int) ((milliseconds / 10000000L)
				- ((milliseconds / 1000000000L) * 100L));
		final int second = (int) ((milliseconds / 100000L)
				- ((milliseconds / 10000000L) * 100L));
		final int nanosecond = (int) ((milliseconds / 100L)
				- ((milliseconds / 100000L) * 1000L));
		return LocalDateTime.of(year, month, day, hour, minute, second,
				(nanosecond * 1000000));
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
	public int compareTo(final TimeId timeId) {
		return (int) (milliseconds - timeId.milliseconds);
	}

	@Override
	public int hashCode() {
		return (int) (31 * milliseconds);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof TimeId
				&& milliseconds == TimeId.class.cast(o).milliseconds;
	}

	private static long localDateTimeToLong(final LocalDateTime dateTime) {
		return dateTime.getYear() * 1000000000000000L
				+ dateTime.getMonthValue() * 10000000000000L
				+ dateTime.getDayOfMonth() * 100000000000L
				+ dateTime.getHour() * 1000000000L
				+ dateTime.getMinute() * 10000000L
				+ dateTime.getSecond() * 100000L
				+ (dateTime.getNano() / 10000L);
	}
}
