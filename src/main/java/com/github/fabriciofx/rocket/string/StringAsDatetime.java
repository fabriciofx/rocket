package com.github.fabriciofx.rocket.string;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class StringAsDatetime {
	private final CharSequence datetime;
	private final String pattern;
	
	public StringAsDatetime(final CharSequence datetime) {
		this(datetime, "dd/MM/yyyy HH:mm");
	}
	
	public StringAsDatetime(final CharSequence datetime, final String pattern) {
		this.datetime = datetime;
		this.pattern = pattern;
	}
	
	public LocalDateTime dateTime() {
		return LocalDateTime.parse(datetime,
			DateTimeFormatter.ofPattern(pattern)
		);
	}
}
