package com.github.fabriciofx.rocket.misc;

import java.time.LocalDateTime;

public final class LocalDateTimeToMilliseconds {
	private final LocalDateTime dateTime;
	
	public LocalDateTimeToMilliseconds(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public long toLong() {
		return dateTime.getYear() * 1000000000000000L
				+ dateTime.getMonthValue() * 10000000000000L
				+ dateTime.getDayOfMonth() * 100000000000L
				+ dateTime.getHour() * 1000000000L
				+ dateTime.getMinute() * 10000000L
				+ dateTime.getSecond() * 100000L
				+ (dateTime.getNano() / 10000L);
	}
}
