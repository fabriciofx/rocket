package com.github.fabriciofx.rocket.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class StringDatetime implements Format<LocalDateTime> {
	private final CharSequence datetime;
	private final String pattern;
	
	public StringDatetime(final CharSequence datetime) {
		this(datetime, "dd/MM/yyyy HH:mm");
	}
	
	public StringDatetime(final CharSequence datetime, final String pattern) {
		this.datetime = datetime;
		this.pattern = pattern;
	}
	
	@Override
	public LocalDateTime format() {
		return LocalDateTime.parse(datetime,
			DateTimeFormatter.ofPattern(pattern)
		);
	}
}
