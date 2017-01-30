package com.github.fabriciofx.rocket.misc;

import java.time.LocalDateTime;

public final class Milliseconds {
	private final long time;
	
	public Milliseconds(final long time) {
		this.time = time;
	}
	
	public LocalDateTime dateTime() {
		final int year = (int) (time / 1000000000000000L);
		final int month = (int) ((time / 10000000000000L)
				- ((time / 1000000000000000L) * 100L));
		final int day = (int) ((time / 100000000000L)
				- ((time / 10000000000000L) * 100L));
		final int hour = (int) ((time / 1000000000L)
				- ((time / 100000000000L) * 100L));
		final int minute = (int) ((time / 10000000L)
				- ((time / 1000000000L) * 100L));
		final int second = (int) ((time / 100000L)
				- ((time / 10000000L) * 100L));
		final int nanosecond = (int) ((time / 100L)
				- ((time / 100000L) * 1000L));
		return LocalDateTime.of(
			year,
			month,
			day,
			hour,
			minute,
			second,
			(nanosecond * 1000000)
		);
	}
}
