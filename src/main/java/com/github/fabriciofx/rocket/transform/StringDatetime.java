package com.github.fabriciofx.rocket.transform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class StringDatetime implements Transform<LocalDateTime> {
	private final transient CharSequence datetime;
	private final transient String pattern;
	
	public StringDatetime(final CharSequence datetime) {
		this(datetime, "dd/MM/yyyy HH:mm");
	}
	
	public StringDatetime(final CharSequence datetime, final String pattern) {
		this.datetime = datetime;
		this.pattern = pattern;
	}
	
	@Override
	public LocalDateTime transform() {
		return LocalDateTime.parse(datetime,
			DateTimeFormatter.ofPattern(pattern)
		);
	}
}
