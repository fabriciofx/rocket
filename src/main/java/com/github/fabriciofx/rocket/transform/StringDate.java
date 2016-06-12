package com.github.fabriciofx.rocket.transform;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class StringDate implements Transform<LocalDate> {
	private final transient CharSequence date;
	private final transient String pattern;

	public StringDate(final CharSequence date) {
		this(date, "dd/MM/yyyy");
	}
	
	public StringDate(final CharSequence date, final String pattern) {
		this.date = date;
		this.pattern = pattern;
	}
	
	@Override
	public LocalDate transform() {
		return LocalDate.parse(date,
			DateTimeFormatter.ofPattern(pattern)
		);
	}
}
